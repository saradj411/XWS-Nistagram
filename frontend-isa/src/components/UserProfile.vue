<template>
    <div>
        <div class="sideView">
            <br>
            <!--<div>
                <div id='dropdownlist' style="background-color: grey; width: 80%; color:  white; margin: auto;">
                    <ejs-dropdownlist :dataSource='allusers' :fields='localFields'
                    placeholder="Search..." allowFiltering='true'>
                    </ejs-dropdownlist>     
                </div>
            </div>-->
    
            <input type="text" class="form-control" v-model="search"
             placeholder="Serach..." style="width: 80%; margin: auto; background-color: black; color: white;" 
             v-on:keyup.enter="changeVisibility" />
         <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "notificationAction()">
                Notification  <b-icon icon="bell-fill" style="margin-left:5px;" color="orange" v-if="this.notSeenNotification > 0 " aria-hidden="true" ></b-icon><label v-if="this.notSeenNotification > 0" style="color:orange; margin-left:3px;"> {{ this.notSeenNotification }} </label> 
                             <b-icon icon="bell" v-if="this.notSeenNotification == 0" aria-hidden="true" style="margin-left:5px;"></b-icon><label></label>
                </b-button>
            <div class="sideViewContent">
            <div class="container" >  
                <div class="row" style="margin-top:10px;">
                    <div class="col-sm">
                    Following  <br> {{ this.following.length }}
                    </div>
                    <div class="col-sm">
                    Followers <br>{{ this.followers.length }}
                    </div>
                   
                </div>
                </div>

                <div>
                    <img src="../assets/gizmo.jpg" width="100px" style="margin: 10px; margin-top:20px;" height="100px">
                </div>           
                <p>{{loggedUser.name}} {{loggedUser.surname}} </p>
                <p><i> @{{loggedUser.username}}</i></p>
                <p>Biography:<br> {{this.myProfile.biography}}</p>

                <p style="color:white"><i>Category: {{this.loggedUser.category}}</i></p>

                
            </div>
            
            <b-button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "update"><b-icon icon="person" aria-hidden="true"></b-icon> Update </b-button>
            <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "requestPage"><b-icon icon="emoji-wink" aria-hidden="true"></b-icon> Request for follow  </b-button>
            <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click="showCloseFriend" ><b-icon icon="heart-fill"  aria-hidden="true"></b-icon> Close friends </b-button>
            <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "request">
                <b-icon icon="tools" aria-hidden="true"></b-icon> Sent a request for verification </b-button>
 
                    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "addStory">
                    <b-icon icon="camera" aria-hidden="true"></b-icon> Add story</b-button> 
                    
                    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "addPosts">
                        <b-icon icon="camera" aria-hidden="true"></b-icon> Add post</b-button>

                    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "publicProfile">
                        <b-icon icon="camera" aria-hidden="true"></b-icon> View public profile</b-button>


                <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "post">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Posts</b-button>   
                <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "story">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Stories</b-button>   
                <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "istaknuto">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Highlights</b-button>   
                    
                      <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "favorites">
                    <b-icon icon="heart-fill" aria-hidden="true"></b-icon> Favorites</b-button> 


                    <b-button class="btn btn-dark"  style=" margin-top: 10px; width: 80%;" v-on:click = "likePosts">
                    <b-icon  icon="hand-thumbs-up" aria-hidden="true"></b-icon> Like posts</b-button> 


                    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "dislikePosts">
                    <b-icon icon="hand-thumbs-down" aria-hidden="true"></b-icon> Dislike posts</b-button> 
            <button class="btn btn-dark" style=" margin-top: 10px; width: 80%; " v-on:click = "logOut"> Log out </button>
           
            
        </div> 
           
			<div class="fotos" v-bind:class="[!visible ? 'show' : 'hide', 'shadow-lg']" >
            <div>
                
<b-button  class="btn btn-dark" style="margin-top: 10px; margin-left:10px; width: 40%; color:white;" v-on:click="viewPost()" >
     <b-icon icon="image" aria-hidden="true"> </b-icon> Friend's posts </b-button>

<b-button  class="btn btn-dark" style="margin-top: 10px; margin-left:10px; width: 40%; color:white;" v-on:click="viewStory()" >
     <b-icon icon="image"  aria-hidden="true"> </b-icon> Friend's stories </b-button>

<!--STORYYY!-->
  <div v-if="showStory" style="float: left; margin: 15px;">  
         
             <b-card class="post_look" v-for="st in stories" v-bind:key="st.idStory">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{st.username}}</h4>
                        </b-row>
             <h6 align="left">{{st.location}}</h6>
                      
                 <div>
                    <b-img v-if="!st.media.fileName.includes(videoText)" thumbnail  v-bind:src="st.media.imageByte" alt="Image 1"></b-img>
                             <video v-if="st.media.fileName.includes(videoText)" autoplay controls v-bind:src="st.media.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div>      
                 
                  <h4 align="left" style="margin-top:-5px;">{{st.description}}</h4>
                   <h5 align="left"><span v-for="(tag,t) in st.tags" :key="t">
                                        #{{tag.tagText}}
                                    </span>
                        </h5>
           
        </b-card>
        
        </div> 

         <!--FRIEND'S POSTS-->





<div style="float: left; margin:15px;" v-if="postovi">  
         <!--FRIEND'S POSTS-->
             <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                     
                        
                        </b-row>
             <h6 align="left">{{post.location}}</h6>
                        
                 <div v-for="m in post.media" v-bind:key="m.imageBytes">
                    <b-img v-if="!m.fileName.includes(videoText)" thumbnail  v-bind:src="m.imageByte" alt="Image 1"></b-img>
                             <video v-if="m.fileName.includes(videoText)" autoplay controls v-bind:src="m.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div> 
<h5 align="right"> <b-icon icon="exclamation" aria-hidden="true" align="right" @click="content($event,post)"></b-icon>inappropriate content</h5>
     
                  <h4 align="left" style="margin-top:-5px;">{{post.description}}</h4>
                   <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.tagText}}
                                    </span>
                        </h5>
                        
             <h5 v-if="numberOfLikes==0" align="left"><b-icon  icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}} likes </h5>
              <h5 v-if="numberOfLikes!=0" align="left"><b-icon  icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{numberOfLikes}} likes </h5>
            <h5 v-if="numberOfDislikes==0" align="left"> <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
            </h5>
            <h5 v-if="numberOfDislikes!=0" align="left"> <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
            </h5>

            
            <h5 align="left"> <b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveInFavorites($event,post)"></b-icon></h5>
   
<span  style="float:left;margin:15px">
                    
          <div class="input-group mb-3">
              <input type="text" style="width:500px;" v-model="comment" class="form-control" placeholder="Add comment" aria-label="Enter username" aria-describedby="addon-wrapping">
              <div class="input-group-append">
                  <b-button class="btn btn-info" style="background:gray;color:white;margine-left:-2%" v-on:click = "getComments(post)" type="button"  ><b-icon icon="plus-circle" aria-hidden="true"></b-icon> 
                Share</b-button>
                </div>
           </div>
      </span>
      <section>
                <b-button 
                variant="outline-secondary"  
                v-on:click = "allComment(post)"
                style="margin-top: 0% !important;
                margin-right: 83%;
                color: #406b99;
                width: 250px;
                hight: 500px;"
                                

                >
                <b-icon icon="chat-square" aria-hidden="chat-square"></b-icon> 
                See all comments
                </b-button>
                 </section>

        </b-card>
        
        </div>

        
         <!--FRIEND'S POSTS-->
         
 <div v-if="bp" style="background:lightgray;">  
       <label style="font-size:28px;color:black">All comments</label>

<div  style=" width:700px;margin-left:30px;margin-top:60px;"  v-for="d in this.comments"  v-bind:key="d.id">
      
        <form>  
           <table  id="table2" class="table" >

              <tbody>
      
    <tr style="font-size:22px;color:black;background:white;width:10px;">@{{d.username}}  
      {{d.txt}}   
    
    </tr>
  
    
  </tbody>
                        </table>



                </form>

               </div>
                                                       <button class="btn btn-primary btn-lg"  v-on:click = "back" style="margin-left:28px; margin-top:42px;">GO BACK</button>



 </div>









            </div>
        </div>  
        
        <div class="suggestions"  v-bind:class="[visible ? 'show' : 'hide', 'shadow-lg']">
            <div style="width: 100%;">
                <h1 style="margin-top: 10px;"> Searh profile: </h1>
                <button type="button" class="close  btn pull-right"  @click="changeVisibilityFalse" aria-label="Close">
                    <span aria-hidden="true"> X </span>                    
                  </button>
                  <div v-for="user in this.searchUsername" class="userDiv" v-bind:key="user.username" @click="lookProfile(user.username)">
                   @{{user.username}} - {{user.name}} {{user.surname}}

                 </div>

            </div>
        </div>  

        <div class="requestPageClass"  v-show="!requestVisible">
            <div style="width: 100%; " >
                <h3 style="margin-top: 20px; color: black;"> Your follow request: </h3>
                <button type="button" style=" margin-top: -40px; color: black;" class="close  btn pull-right"  @click="requestVisible = !requestVisible " aria-label="Close">
                    <span aria-hidden="true"> X </span>                    
                  </button>

                  <div class="container" style="">
                    <div class="row" style="margin-left: -10px;
                        margin-right: -10px;
                        padding: 5px;
                        margin-top: 5px;
                        border-width: 2px; background-color: black; color: white;
                        border-color: black;
                        border-style: solid;" v-for="req in this.followerRequest" v-bind:key="req.username">
                        <div class="col-2">
                            <img src="../assets/gizmo.jpg" width="100px" height="100px"/>
                        
                        
                      </div>
                      <div class="col-8">
                        <h4 style="position: relative;
                        float: left;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);">@{{req.username}}</h4>
                      </div>
                      <div class="col" >
                        <button  type="button" @click="acceptRequest(req.username)"  style="position: relative;
                        float: left;
                        top: 100%;
                        left: 50%;
                        transform: translate(-50%, -50%); margin-left:-5px;" class="close  btn pull-right" aria-label="Add">
                            <b-icon icon="check-square" aria-hidden="true"></b-icon>          
                          </button>
                      </div>
                      <div class="col">
                        <button  type="button"   @click="declineRequest(req.username)" style="position: relative;
                        float: left;
                        top: 100%;
                        left: 50%;
                        transform: translate(-50%, -50%); margin-left:-10px;" class="close  btn pull-right" aria-label="Add">
                            <b-icon icon="x-square" aria-hidden="true"></b-icon>          
                          </button>
                      </div>
                    </div>
                   
                  </div>



    



            </div>
        </div>  

              <div class="notificationPage" v-if="this.showModalForNotification">
                    <h1 style="color:black;">Nofitifacions: </h1>
                    <button type="button" style=" margin-top: -40px; color: black;" class="close  btn pull-right"  @click="showModalForNotification = !showModalForNotification" aria-label="Close">
                    <span aria-hidden="true"> X </span></button>

                <div class="row" style="margin-left: 10px;
                        margin-right: 10px;
                        padding: 5px;
                        margin-top: 5px;
                        border-width: 2px; background-color: black; color: white;
                        border-color: black;
                        border-style: solid;
                        cursor: pointer;" v-for="one in this.notifications.slice().reverse()" v-on:click="goTo(one)" v-bind:key="one.id">
                        <div class="col-2">
                            <img src="../assets/gizmo.jpg" width="100px" height="100px"/>
                        
                        
                      </div>
                      <div class="col-8">
                        <h4 style="position: relative;
                        float: left;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);">{{one.text}}</h4>
                      </div>                     
                    </div>        
                    
                </div>

                <div class="closeFriendsPage" v-if="this.showModalForCloseFriends">
                <h1 style="color:black;">Your close friends: </h1>
                    <button type="button" style=" margin-top: -40px; color: black;" class="close  btn pull-right"  @click="showModalForCloseFriends = !showModalForCloseFriends" aria-label="Close">
                    <span aria-hidden="true"> X </span></button>

                <div class="row" v-for="friend in this.closeFriends"  v-bind:key="friend.username"   style="margin-left: 10px;
                        margin-right: 10px;
                        padding: 5px;
                        margin-top: 5px;
                        border-width: 2px; background-color: black; color: white;
                        border-color: black;
                        border-style: solid;
                        cursor: pointer;" >
                        <div class="col-2">
                            <img src="../assets/gizmo.jpg" width="100px" height="100px"/>
                        
                        <!--v-for="one in this.notifications.slice().reverse()" v-on:click="goTo(one)" v-bind:key="one.id" -->
                      </div>
                      <div class="col-8" v-on:click="closeFriendShowProfile(friend)">
                        <h4 style="position: relative;
                        float: left;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);">@{{friend.username}}</h4>
                      </div>  
                      <div class="col">
                        <button  type="button"  @click="deleteCloseFriend(friend.username)"  style="position: relative;
                        float: left;
                        top: 100%;
                        left: 50%;
                        transform: translate(-50%, -50%); margin-left:-10px;" class="close  btn pull-right" aria-label="Add">
                            <b-icon icon="x-square" aria-hidden="true"></b-icon>          
                          </button>
                      </div>                   
                    </div>   
                </div>

        
    </div>
</template>
<script>
export default {
  data() {
    return {   
         posts: [],
        loggedUser: {} ,
        myProfile: {},
        searchUsername: [],
        search: "",
        selected: null,
        allusers: [],
        item:{},
                comments:[],

        localFields:
        {
            value: 'username', text: 'username'
        },
        itemTemplate:
        {
            value: 'username', text: 'username'
        },

        value: "",
         videoText: "mp4",
       notifications: [],
       notSeenNotification: 0,
        
        requestVisible: true,
        showModalForNotification: false,
        followerRequest: [],

        postovi:true,
        bp:false,
         comment:[],
         numberOfLikes :0,
        numberOfDislikes:0,

        following: [],
        followingNum:0,
        followers:[],
        followersNum:0,
        visible: false,

        closeFriends: [],
        showModalForCloseFriends: false,

        //showPost:true,
        showStory:false,

    }
  },
  computed:{

    computedVisibility: function() {
            return this.visibility;
        }
  },
  methods:{ 
        viewPost:function(){
              this.postovi=true
              this.showStory=false
          },
          viewStory:function(){
              this.postovi=false
              this.showStory=true
          },
         allComment: function(post){
            //alert("idemooo");
            //alert("logovani komentarise "+this.loggedUser.username);
            //alert("komentar "+this.comment);
            //alert("kome komentarise sliku "+post.username);
            //alert("id posta "+post.idPost);
            this.postovi = false;
                        this.bp = true;


            this.axios.get('/media/post/getComments/'+post.idPost,{ 
                }).then(response => {
                    //alert("prikaziii");
                    this.comments = response.data;
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
      getComments: function(post){
            //alert("idemooo");
            //alert("logovani komentarise "+this.loggedUser.username);
            //alert("komentar "+this.comment);
            //alert("kome komentarise sliku "+post.username);
            //alert("id posta "+post.idPost);
                        



            const postInfo = {
                usernameFrom : this.loggedUser.username,
                usernameTo:post.username,
                comment : this.comment,
                postId: post.idPost

            }
            this.axios.post('media/post/commentPost',postInfo,{ 
                }).then(response => {
                    alert("Comment is shared");
                    this.nesto = response.data;
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
      saveInFavorites: async function(event,post){
        console.log(post)
         //alert(this.loggedUser.username)
         //alert(post.idPost)
            this.axios.post('media/favorites/saveInFavorites/'+this.loggedUser.username+"/"+post.idPost,{ 
                

                }).then(response => {
                    alert("Post saved in favorites!");
                     
                    console.log(response);                
                }).catch(res => {
                    alert("You have already saved this post");
                    console.log(res.response.data.message);

                });


        },
        content: async function(event,post){
            this.axios.post('media/post/report/'+post.idPost,{ 
                

                }).then(response => {
                    alert("Post was reported as inappropriate content!");
                     
                    console.log(response);                
                }).catch(res => {
                    alert("You have already saved this post");
                    console.log(res.response.data.message);

                });


        },

    likePost: async function(event,post){
        console.log(post)
         
            this.axios.post('media/post/like/'+this.loggedUser.username+"/"+post.idPost,{ 
                }).then(response => {
                    alert("Picture is liked!");
                    this.likesNumber = response.data
                    this.numberOfLikes = this.likesNumber
                     
                    console.log(response);                
                }).catch(res => {
                    alert("You have already liked this post");
                    console.log(res.response.data.message);

                });


        },

         dislikePost: async function(event,post){
        console.log(post)
         
            this.axios.post('media/post/dislike/'+this.loggedUser.username+"/"+post.idPost,{ 
                }).then(response => {
                    alert("Picture is disliked!");
                    this.dislikesNumber = response.data
                    this.numberOfDislikes = this.dislikesNumber
                     
                    console.log(response);                
                }).catch(res => {
                    alert("You have already liked this post");
                    console.log(res.response.data.message);

                });


        }
    ,


      closeFriendShowProfile: function(friend)
      { console.log(friend);
                this.$router.push('/AnotherUserProfile/'+ friend.username);
      },
      deleteCloseFriend: function(username)
      {
            console.log(username);

            const userForFollow = 
            {
                username: username
            }
   
              this.axios.post('/profile/api/profile/deleteNewCloseFriend', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.showModalForCloseFriends = false;                     
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
      },

      showCloseFriend: function()
      {
          
          this.showModalForCloseFriends = true;
            this.axios.get('/profile/api/profile/getAllCloseFriends',{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {        
                        this.closeFriends = response.data;            
                       console.log(response.data);                       
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    }); 


      },
      goTo: function(one)
      {
          console.log("FO TO");
          console.log(one);
          if(one.type == "FOLOW_REQUEST")
          {
              
              this.showModalForNotification = false;
              this.requestVisible = false;
          }
          else if(one.type == "FOLLOW")
          {
              console.log("foloowers");              
             this.$router.push('/AnotherUserProfile/'+ one.fromWhoUsername.username);
          }
          else if(one.type == "POST")
          {
              this.$router.push('/AnotherUserProfile/'+ one.fromWhoUsername.username);
          }
      },
      notificationAction: function()
      {
    this.showModalForNotification = true;
         const notImportant =
        {
            username: this.loggedUser.username
        }
          this.axios.post('/profile/api/notification/seenNotification',notImportant,{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                    
                       console.log(response);
                       this.notSeenNotification = 0;
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   
      
      },

      acceptRequest: function(username)
      {
        console.log(username);  
        const accept =
        {
            username: username
        }

        this.axios.post('/profile/api/profile/acceptFollowRequest',accept,{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {  //this.$router.go(this.$router.currentRoute)                      
                       console.log(response);



                       window.location.reload();
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });    
      },
      declineRequest: function(username)
      {
        console.log(username);  
        const decline =
        {
            username: username
        }

        this.axios.post('/profile/api/profile/declineFollowRequest',decline,{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {    
                        window.location.reload();                   
                       console.log(response);
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });    
      },

       back: function(){
         this.bp = false;

          this.postovi = true;
      },

    requestPage: function()
    {
        this.requestVisible = !this.requestVisible;
        this.showModalForNotification = false
        this.showModalForCloseFriends = false;
    }  ,
        logOut: function(){
      this.axios.post('/profile/api/users/logout',{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                       console.log(response);
                        window.location.href = "/"
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                        window.location.href = "/"
                       
                    });    
        },
     update: function(){
        window.location.href = "/UpdateAccount";
      },

      request: function(){
        window.location.href = "/VerificationRequest";
      },
                
        addPosts: function(){
        window.location.href = "/AddPost";
      },
      addStory: function(){
        window.location.href = "/AddStory";
      },
      post: function(){
        window.location.href = "/Proba";
      },  
      story: function(){
        window.location.href = "/Stories";
      },  
      istaknuto: function(){
        window.location.href = "/HighlightsStories";
      },    
    favorites: function(){
        window.location.href = "/Favorites";
      }, 

publicProfile: function(){
        window.location.href = "/ProfilePublic";
      },





    dislikePosts: function(){
        window.location.href = "/DisLikePosts";
      },      
    likePosts: function(){
        window.location.href = "/LikePosts";
      }, 
      searchFunction: function()  
      {
          console.log(this.search);
          document.getElementsByClassName('suggestions').style.visibility = 'visible';          
          document.getElementsByClassName('fotos').style.visibility = 'hidden';

      },
      lookProfile: function(username)
      {
        window.location.href = "/AnotherUserProfile/"+username;
      },
      changeVisibility() {
        this.visible = true;
        const promenljiva =
        {
            username: this.search
        }
        this.axios.post('/profile/api/users/searhUsername', promenljiva, {
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                       this.searchUsername = response.data;
                       console.log(this.searchUsername);
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });     

    },
    changeVisibilityFalse() {
        this.visible = false;
    }

      },
      


mounted() {         
           

    this.axios.get('/profile/api/users/getLoggedUser',{
                    headers:{}}).then(response => 
                    {                        
                       this.loggedUser = response.data;
                       const info = 
                    {
                        username: this.loggedUser.username
                    }    
                    
                    this.axios.post('/profile/api/profile/getProfileByUsername',info ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                this.myProfile = response.data;


                                this.axios.post('/profile/api/profile/getAllRequestTo',info ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                this.followerRequest = response.data;                                 

                            }).catch(res => {                        
                                    console.log(res.response.data.message);
                                    });
                            }).catch(res => { console.log(res.response.data.message); });
                    

 this.axios.post('/profile/api/profile/getAllFollowing',info ,{ 
         headers: {
                    'Content-Type': 'application/json;charset=utf-8' 
                  }
                            }).then(response => {
                                this.following = response.data;                                 

                            }).catch(res => {                        
                                    console.log(res.response.data.message);
                                    });
 this.axios.post('/profile/api/profile/getAllFollowers',info ,{ 
         headers: {
                    'Content-Type': 'application/json;charset=utf-8' 
                  }
                            }).then(response => {
                                this.followers = response.data;                                 

                            }).catch(res => {                        
                                    console.log(res.response.data.message);
                                    });
//FRIENDS POOOOOOSTS
this.axios.get('/media/post/getPostOfFollowing/'+this.loggedUser.username)
            .then(response => {
                this.posts = response.data;
                let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                 for(let j=0; j< this.posts[k].media.length; j++){
                    if(!this.posts[k].media[j].fileName.includes(video)){
                                console.log("usao je u if");
                                this.posts[k].media[j].imageByte = 'data:image/jpeg;base64,' + this.posts[k].media[j].imageByte;
                            }else{
                                this.posts[k].media[j].imageByte = 'data:video/mp4;base64,' + this.posts[k].media[j].imageByte;       
                            }  
                            console.log("uslo");
                        }
                 }
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
///OTHER STORIES
             this.axios.get('/media/story/getStoryOfFollowing/'+this.loggedUser.username)
            .then(response => {
                this.stories = response.data;
                let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                   if(!this.stories[k].media.fileName.includes(video)){
                                console.log("usao je u if");
                                this.stories[k].media.imageByte = 'data:image/jpeg;base64,' + this.stories[k].media.imageByte;
                            }else{
                                this.stories[k].media.imageByte = 'data:video/mp4;base64,' + this.stories[k].media.imageByte;       
                            }  
                            console.log("uslo");
                        
                 }
              
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
const notImportant =
{
    username: this.loggedUser.username
}
this.axios.post('/profile/api/notification/getUserNofitcation',notImportant ,{
                                        headers: {}} ).then(response => 
                                        {     
                                             this.notifications = response.data; 
                                             console.log(this.notifications);                                             
                                             for(var notif in this.notifications)
                                             {
                                                 if(this.notifications[notif].notificationSeen == false)
                                                 {console.log("ima obavestenja koje nije video");
                                                     this.notSeenNotification += 1;
                                                 }
                                             }

                                             console.log("BROJ : " + this.notSeenNotification);
                                              
                                        }).catch(res => {   
                                            console.log(res.response);
                                        
                                        });


                                    }).catch(res => {                        
                                                        
                                        console.log(res.response);
                                        
                                    
                                    }); 
    

                            



            }
     
}


</script>

<style>
.sideView
{
    color: white;
    background-color: black;
    width: 25%;

    height: 125vh;

    height: fit-content;

    position: absolute;
    margin-top: -60px;
}

.close
{
    color: red;
position: absolute;
margin: -50px 38%;
}
.sideViewContent
{
    display: inline-grid;
}
.fotos
{
    background-color: gray;
    color: black;
width: 70%;
height: 125vh;
position: absolute;
display: -webkit-inline-box;
margin-left: -25%;
margin-top: -60px;
}

.suggestions
{
background-color: gray;
color: black;
width: 80%;
height: 100vh;
position: absolute;
display: -webkit-inline-box;
margin-left: -25%;
margin-top: -60px;
visibility: hidden;
}

.show {
    visibility: visible
  }
.hide {
    visibility: hidden
  }
.userDiv
{
    background-color: black;
    color: white;
    margin: 0 auto;
    margin-top: 0px;
    padding: 10px;
    width: 80%;
    margin-top: 10px;
}
.requestPageClass
{
    margin: 0 auto;
    width: 75%;
    height: 100vh;
    background-color: gray;
    position: absolute;
    margin-left: 25%;    
    margin-top: -60px;
    
}

.notificationPage
{
    background-color: gray;
    width: 75%;
    height: 100vh;
    position: absolute;
    margin-top: -60px;
    margin-left: 25%;
}

.closeFriendsPage
{
    background-color: gray;
    width: 75%;
    height: 100vh;
    position: absolute;
    margin-top: -60px;
    margin-left: 25%;
}
</style>