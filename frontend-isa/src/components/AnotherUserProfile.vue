<template>
<div class="profile">
    <table style="margin-top: 50px; position: relative;">
        <tr>
        <td>
            <img src="../assets/gizmo.jpg" width="200px" height="200px" style="margin-left: -50px; position: absolut;"/></td>

        <td>   
         <h1 style="color:white;"> {{this.userProfile[0].name}} {{this.userProfile[0].surname }} </h1>

        <td>
         <h1 style="color:white;">{{this.userProfile[0].name}} {{this.userProfile[0].surname }} </h1>

    <p><i> @{{ this.userProfile[0].username }} </i></p>
    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%; color:white;" :disabled="isBlocked" v-on:click = "follow()"> 
    <b-icon icon="plus" aria-hidden="true"></b-icon>
     {{ text }} 

    <b-icon icon="lock" v-show="this.userProfileClassInfo.privateProfil == true && this.requestStatus == false && this.followingStatus == false && this.postovi == false" aria-hidden="true"></b-icon> </b-button> 

    <b-icon icon="lock" v-show="this.userProfileClassInfo.privateProfil == true && this.requestStatus == false && this.followingStatus == false" aria-hidden="true"></b-icon> 
   
    <b-button class="btn btn-dark" v-if="this.followingStatus && !this.isBlocked" style=" margin-left: 10px;  margin-top: 10px; width: 15%; color:white;" v-on:click = "closeFriend()">
     <b-icon icon="star" v-if="this.isCloseFriend == false" aria-hidden="true"></b-icon> 
     <b-icon icon="star-fill" v-if="this.isCloseFriend  == true" aria-hidden="true" color="green"></b-icon>  </b-button>

 <b-button class="btn btn-dark" v-if="this.followingStatus && !this.isBlocked" v-on:click="mute()" style="margin-top: 10px; width: 31%; color:white;" >
     <b-icon icon="eye-fill" v-if="!this.isMuted" aria-hidden="true"></b-icon> 
     <b-icon icon="eye-slash" v-if="this.isMuted" aria-hidden="true"></b-icon> Mute </b-button>



      <!--<b-button class="btn btn-dark" v-if="this.followingStatus && !this.isBlocked" style="margin-top: 10px; margin-left:10px; margin-right:10px; width: 31%; color:white;" >
     <b-icon icon="bell-fill" aria-hidden="true"></b-icon>
     <b-icon icon="bell" aria-hidden="true"></b-icon>  Notification  </b-button>-->
      <b-button class="btn btn-dark" style="margin-top: 10px; margin-left:10px; width: 31%; color:white;" v-on:click="block()" >
     <b-icon icon="exclamation-circle" v-if="!this.isBlocked" aria-hidden="true"> </b-icon>
    <b-icon icon="exclamation-circle-fill" v-if="this.isBlocked" color="red" aria-hidden="true"> </b-icon> {{ this.blockText }} </b-button>




    </td>
    
        </tr>
        <tr>
        <td></td>
        <td>
   

    </td>
        </tr>
    </table>

    <div v-show="this.userProfileClassInfo.privateProfil == true && followingStatus == false && this.postovi ==false ">
        <h3 style="margin-top: 100px;"> Profile is private! To see posts you need to follow this user! </h3>
        <img src="../assets/lock.png" style="width: 200px; height: 200px; margin-top: 40px;" aria-hidden="true"><img>

    </div> 


    


<!--Prikazi postove  profila ako ga pratim   v-show="this.userProfileClassInfo.privateProfil == false || this.followingStatus == true"-->
<div  style="float: left; margin: 15px;" v-show="prikaz">  
       

         <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"  style="color:black"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        </b-row>
             <h6 align="left"  style="color:black">{{post.location}}</h6>
                        
                 <div v-for="m in post.media" v-bind:key="m.imageBytes">
                    <b-img v-if="!m.fileName.includes(videoText)" thumbnail  v-bind:src="m.imageByte" alt="Image 1"></b-img>
                             <video v-if="m.fileName.includes(videoText)" autoplay controls v-bind:src="m.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div>      
                  <h4 align="left" style="margin-top:-5px;color:black">{{post.description}}</h4>
                   <h5 align="left"  style="color:black"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.tagText}}
                                    </span>
                        </h5>
            <h5 v-if="numberOfLikes==0" align="left"  style="color:black"><b-icon  icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}} likes </h5>
              <h5 v-if="numberOfLikes!=0" align="left"  style="color:black"><b-icon  icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{numberOfLikes}} likes </h5>
            <h5 v-if="numberOfDislikes==0" align="left"  style="color:black"> <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
            </h5>
            <h5 v-if="numberOfDislikes!=0" align="left"  style="color:black"> <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
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
                margin-right: 78%;
                color: #406b99;
                width: 200px;"
                >
                <b-icon icon="chat-square" aria-hidden="chat-square"></b-icon> 
                See all comments
                </b-button>
                 </section>
        </b-card>
        
        
       </div>

        <!--FRIEND'S POSTS   -->
         
         
 <div  v-if="bp" style="margin-left:500px;background:lightgray;width:1100px;">  
       <label style="font-size:28px;color:black">All comments</label>

<div  style=" width:800px;margin-left:140px;margin-top:60px;"  v-for="d in this.comments"  v-bind:key="d.id">
      
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
</template>

<script>
 export default {
      data() {
        return { 
            username : this.$route.params.username,
            userProfile: {},
            sendedRequest: [],
            loggedUser: {},
            exists: false,
            text: "Follow",

            postovi:false,

            blockText: "Block",

            followingStatus: false,
            requestStatus: false,
            following: [],
            userProfileClassInfo: {},

            comments:[],
            prikaz:false,
            value: "",
            posts:[],
       
        
            requestVisible: true,

            followerRequest: [],
            bp:false,
            

            closeFriends:[],
            isCloseFriend: false,

            mutedProfiles:[],
            isMuted: false,

            blockedProfile:[],
            isBlocked:false,

            notifications: [],
            notSeenNotification: 0


        }
      },
      methods:{
           allComment: function(post){
            //alert("idemooo");
            //alert("logovani komentarise "+this.loggedUser.username);
            //alert("komentar "+this.comment);
            //alert("kome komentarise sliku "+post.username);
            //alert("id posta "+post.idPost);
          this.bp = true;
          this.prikaz = false;
                      




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


        },
         back: function(){
this.prikaz = true;
          this.bp = false;
      },

          mute:function()
          { 
              const userForFollow = 
            {
                username: this.userProfile[0].username
            }              
            if(this.isMuted == true)
              {
                  //unmute
                  this.axios.post('/profile/api/profile/unmuteProfile', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {

                                this.isMuted = false;      
                                console.log(response.data);                              
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
              }
              else
              {
                  this.axios.post('/profile/api/profile/muteProfile', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                                                
                                this.isMuted = true;      
                                console.log(response.data);                              
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });

              }




          },
          block: function()
          {
               const userForFollow = 
            {
                username: this.userProfile[0].username
            }
              if(this.isBlocked == true)
              {
                  //odblokiraj
                  console.log("ODBLOKIRA");
                  this.axios.post('/profile/api/profile/unblockProfile', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                this.text = "Follow";
                                this.blockText = "Block";
                                this.isMuted = false;                                  
                                this.followingStatus = false;                              
                                this.isCloseFriend = false;
                                console.log(response.data);
                                this.isBlocked = false;                                
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
              }
              else
              {
                  //blokiraj
                   this.axios.post('/profile/api/profile/blockProfile', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.isBlocked = true;   
                                this.text = "This user is blocked!";
                                this.blockText = "Unblock"  
                                this.isCloseFriend = false;
                                this.followingStatus = false;
                                this.isMuted = false;                           
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
              }
          },
          
          closeFriend: function(){

              const userForFollow = 
            {
                username: this.userProfile[0].username
            }

            if(this.isCloseFriend == true)
            {
                console.log("Izbaci iz bliskih");
              this.axios.post('/profile/api/profile/deleteNewCloseFriend', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.isCloseFriend = false;                                
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
            }else{
              console.log("Add close friend.");
              this.axios.post('/profile/api/profile/addNewCloseFriend', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.isCloseFriend = true;                                
                             
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
            }
          },
        follow: function(){
            const userForFollow = 
            {
                username: this.userProfile[0].username
            }
            console.log(this.userProfile[0]);

            if(this.userProfileClassInfo.privateProfil == true)
            { 
                this.postovi = false;
                console.log("ZAHTEV ZA PRIVATAN PROFIL");
                // salji zhatev
                if(this.requestStatus == false)
                {
                    console.log("REQUEST FALSE");
                    if(this.followingStatus == true)
                    {
                        this.postovi = true;
                        console.log("Brise onoga ko ga prati");
                        this.axios.post('/profile/api/profile/deleteRequestAndFollow',userForFollow ,{ 
                                                    headers: {
                                                        'Content-Type': 'application/json;charset=utf-8' 
                                                        }
                                                    }).then(response => {
                                                        console.log(response.data);
                                                        this.followingStatus = false;
                                                        this.postovi = false;
                                                        this.text = " Follow ";
                                                    }).catch(res => {                                                                
                                                                console.log(res.response.data.message);
                                                            });
                    }else
                    {
                        this.axios.post('/profile/api/profile/addRequest4follow', userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.requestStatus = true;
                                
                                this.text = " Request sent ";
                    //TODO: Dodati post metodu za slanje nofitikacija


                            const newNotification = 
                            {
                                text: "@"+this.loggedUser.username +" send you a follow request.",
                                toWhoUsername: this.userProfileClassInfo.username, // kome saljem
                                type: "FOLOW_REQUEST"

                            }

                             this.axios.post('/profile/api/notification/addNotification',newNotification ,{ 
                                                    headers: {
                                                        'Content-Type': 'application/json;charset=utf-8' 
                                                        }
                                                    }).then(response => {
                                                        console.log(response.data);                                                     
                                                    }).catch(res => {                                                                
                                                                console.log(res.response.data.message);
                                                            });






                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
                    }
                    //nema poslatog zahteva
                   
                }
                else
                {
                    //zahtev poslat
                        this.axios.post('/profile/api/profile/deleteRequestAndFollow',userForFollow ,{ 
                                                    headers: {
                                                        'Content-Type': 'application/json;charset=utf-8' 
                                                        }
                                                    }).then(response => {
                                                        console.log(response.data);
                                                        this.requestStatus = false;
                                                        this.text = " Follow ";
                                                    }).catch(res => {                                                                
                                                                console.log(res.response.data.message);
                                                            });


                }

            }else
            {   
                this.postovi=true;
                if(this.followingStatus == true)
                {
                this.postovi=true;

                    this.axios.post('/profile/api/profile/deleteRequestAndFollow',userForFollow ,{ 
                                                    headers: {
                                                        'Content-Type': 'application/json;charset=utf-8' 
                                                        }
                                                    }).then(response => {
                                                        console.log(response.data);
                                                        this.followingStatus = false;

                                                        this.text = " Follow ";
                                                        //obrisi i sa bliskih
                                this.axios.post('/profile/api/profile/deleteNewCloseFriend', userForFollow ,{ 
                                                            headers: {
                                                                'Content-Type': 'application/json;charset=utf-8' 
                                                                }
                                                            }).then(response => {
                                                                console.log(response.data);
                                                                this.isCloseFriend = false;                                
                                                            
                                                            }).catch(res => {
                                                                        console.log(res.response.data.message);
                                                                    });


                                                    }).catch(res => {                                                                
                                                                console.log(res.response.data.message);
                                                            });
                }
                else
                {
                    this.axios.post('/profile/api/profile/addRequest4follow',userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.followingStatus = true;
                                this.postovi=true;
                                this.text = " Following ";
const newNotification = 
                            {
                                text: "@"+this.loggedUser.username +" started following you.",
                                toWhoUsername: this.userProfileClassInfo.username, // kome saljem
                                type: "FOLLOW"

                            }

                             this.axios.post('/profile/api/notification/addNotification',newNotification ,{ 
                                                    headers: {
                                                        'Content-Type': 'application/json;charset=utf-8' 
                                                        }
                                                    }).then(response => {
                                                        console.log(response.data);                                                     
                                                    }).catch(res => {                                                                
                                                                console.log(res.response.data.message);
                                                            });



                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
                }
            }
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
            this.axios.get('/profile/api/profile/viewPosts/'+this.loggedUser.username+"/"+this.username)
            .then(response => {
                  // alert("ajde "+ response.data)
                this.prikaz = response.data;
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
        
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   



        this.axios.get('/media/post/getPostByUsername/'+this.username)
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
        
 





       const profile =
        {
            username: this.username
        }
        this.axios.post('/profile/api/users/searhUsername',profile ,{ 
            headers: {
                   'Content-Type': 'application/json;charset=utf-8' 
                }
            }).then(response => {
                  this.userProfile = response.data;              
            }).catch(res => {                        
                     console.log(res.response.data.message);
                    });

        
        this.axios.post('/profile/api/profile/getProfileByUsername',profile ,{ 
            headers: {
                   'Content-Type': 'application/json;charset=utf-8' 
                }
            }).then(response => {
                  this.userProfileClassInfo = response.data;

            }).catch(res => {                        
                     console.log(res.response.data.message);
                    });

        

                    
        this.axios.get('/profile/api/users/getLoggedUser',{
                    headers:{}}).then(response => 
                    {                        
                       this.loggedUser = response.data;
                       const log = 
                       {
                           username: this.loggedUser.username
                       }
                                    
                            this.axios.post('/profile/api/profile/getAllRequest',log, {
                                        headers: {}} ).then(response => 
                                        {     
                                             this.sendedRequest = response.data;
                                             
                                             for(var u in this.sendedRequest)
                                             { 
                                                 if(this.sendedRequest[u].username == this.userProfile[0].username)
                                                 {
                                                     console.log("USAO")
                                                     this.requestStatus = true;
                                                     this.followingStatus = false;
                                                     this.postovi = false;
                                                     this.text = "Request sent";
                                                     break;
                                                 }
                                             }
                                               
                                        }).catch(res => {   
                                            console.log(res.response);
                                        
                                        }); 

                            this.axios.post('/profile/api/profile/getAllFollowing',log, {
                                        headers: {}} ).then(response => 
                                        {   
                                            this.following = response.data;                                             
                                             for(var u in this.following)
                                             {                                                 
                                                 if(this.following[u].username == this.userProfile[0].username)
                                                 {                                                     
                                                     this.text = " Following ";
                                                     this.followingStatus = true;
                                                     this.postovi=true;
                                                     break;
                                                 }
                                             }
                                              
                                        }).catch(res => {   
                                            console.log(res.response);
                                        
                                        });   

                        this.axios.get('/profile/api/profile/getAllCloseFriends' ,{
                                        headers: {}} ).then(response => 
                                        {     
                                             this.closeFriends = response.data;
                                              console.log(this.closeFriends);
                                             for(var u in this.closeFriends)
                                             { 
                                                 if(this.closeFriends[u].username == this.userProfile[0].username)
                                                 {
                                                    
                                                     this.isCloseFriend = true;                                                    
                                                     break;
                                                 }
                                             } 
                                        }).catch(res => {   
                                            console.log(res.response);
                                        
                                        });   

                          this.axios.get('/profile/api/profile/getMutedProfiles' ,{
                                        headers: {}} ).then(response => 
                                        {     
                                             this.mutedProfiles = response.data;
                                              console.log(this.closeFriends);
                                             for(var u in this.closeFriends)
                                             { 
                                                 if(this.mutedProfiles[u].username == this.userProfile[0].username)
                                                 {
                                                    
                                                     this.isMuted = true;                                                    
                                                     break;
                                                 }
                                             } 
                                        }).catch(res => {   
                                            console.log(res.response);
                                        
                                        });  
                             this.axios.get('/profile/api/profile/getBlockedProfiles' ,{
                                        headers: {}} ).then(response => 
                                        {     
                                             this.blockedProfile = response.data;
                                              console.log(this.closeFriends);
                                             for(var u in this.closeFriends)
                                             { 
                                                 if(this.blockedProfile[u].username == this.userProfile[0].username)
                                                 {                                                    
                                                     this.isBlocked = true; 
                                                     this.text = "This user is blocked!"
                                                     this.blockText = "Unblock"                                                   
                                                     break;
                                                 }
                                             } 
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
body
{
   background-color: black; 
   margin: 0 auto;
    
}

.profile
{  margin-top: -60px;
    background-color: black; 
    height: 100vh;
    color: white;
}

button:background {
  background-color:red;
}

/* nesto javlja gresku nmp
b-button[disabled]{
  border: 1px solid #999999;
  background-color: #white;
  color: #666666;
}*/

  


</style>