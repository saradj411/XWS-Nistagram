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
    
            <div class="sideViewContent">
                <div>
                    <img src="../assets/gizmo.jpg" width="100px" style="margin: 10px; margin-top:20px;" height="100px">
                </div>           
                <p>{{loggedUser.name}} {{loggedUser.surname}} </p>
                <p><i> @{{loggedUser.username}}</i></p>
                <p>Biography:<br> {{this.myProfile.biography}}</p>
                <p><i>Category: {{this.myProfile.category}}</i></p>
                
            </div>
            
            <b-button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "update"><b-icon icon="person" aria-hidden="true"></b-icon> Update </b-button>
            <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "requestPage"><b-icon icon="emoji-wink" aria-hidden="true"></b-icon> Request for follow  </b-button>
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
                <h1> FRIEND'S POSTS : </h1>


         <!--FRIEND'S POSTS-->





<div style="float: left; margin:15px;" v-if="posts">  
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

        </b-card>
        
        </div>

         <!--FRIEND'S POSTS-->









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
                <button type="button" style=" margin-top: -40px; color: black;" class="close  btn pull-right"  @click="requestVisible = !requestVisible" aria-label="Close">
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
              

        
    </div>
</template>
<script>
export default {
  data() {
    return {   
        loggedUser: {} ,
        myProfile: {},
        searchUsername: [],
        search: "",
        selected: null,
        allusers: [],
        item:{},
        localFields:
        {
            value: 'username', text: 'username'
        },
        itemTemplate:
        {
            value: 'username', text: 'username'
        },

        value: "",
       
        
        requestVisible: true,

        followerRequest: [],
        visible: false,
        posts:true
    }
  },
  computed:{

    computedVisibility: function() {
            return this.visibility;
        }
  },
  methods:{ 
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
    requestPage: function()
    {
        this.requestVisible = !this.requestVisible;
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
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                       this.loggedUser = response.data;

                               //alert(this.loggedUser.username)
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
        
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   
     


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
                                
                                console.log("IZVUCE LI ISTA");
                                    console.log(this.followerRequest); 

                            }).catch(res => {                        
                                    console.log(res.response.data.message);
                                    });
                            }).catch(res => { console.log(res.response.data.message); });
/*****************************************************************************************************************************/


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
</style>