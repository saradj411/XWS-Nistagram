<template>
<div class="profile">
    <table style="margin-top: 50px; position: relative;">
        <tr>
        <td>
            <img src="../assets/gizmo.jpg" width="200px" height="200px" style="margin-left: -50px; position: absolut;"/></td>
        <td>
         <h1 style="color:white;">{{this.userProfile[0].name}} {{this.userProfile[0].surname }} </h1>
    <p><i> @{{ this.userProfile[0].username }} </i></p>
    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%; color:white;" :disabled="isBlocked" v-on:click = "follow()"> 
    <b-icon icon="plus" aria-hidden="true"></b-icon>
     {{ text }} 
    <b-icon icon="lock" v-show="this.userProfileClassInfo.privateProfil == true && this.requestStatus == false && this.followingStatus == false" aria-hidden="true"></b-icon> </b-button> 
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

    <div v-show="this.userProfileClassInfo.privateProfil == true && followingStatus == false ">
        <h3 style="margin-top: 100px;"> Profile is private! To see posts you need to follow this user! </h3>
        <img src="../assets/lock.png" style="width: 200px; height: 200px; margin-top: 40px;" aria-hidden="true"><img>

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
            blockText: "Block",
            followingStatus: false,
            requestStatus: false,
            following: [],
            userProfileClassInfo: {},

            closeFriends:[],
            isCloseFriend: false,

            mutedProfiles:[],
            isMuted: false,

            blockedProfile:[],
            isBlocked:false

        }
      },
      methods:{
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
                console.log("ZAHTEV ZA PRIVATAN PROFIL");
                // salji zhatev
                if(this.requestStatus == false)
                {
                    console.log("REQUEST FALSE");
                    if(this.followingStatus == true)
                    {
                        console.log("Brise onoga ko ga prati");
                        this.axios.post('/profile/api/profile/deleteRequestAndFollow',userForFollow ,{ 
                                                    headers: {
                                                        'Content-Type': 'application/json;charset=utf-8' 
                                                        }
                                                    }).then(response => {
                                                        console.log(response.data);
                                                        this.followingStatus = false;
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
                if(this.followingStatus == true)
                {
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
                                this.text = " Following ";
                            }).catch(res => {
                                        console.log(res.response.data.message);
                                    });
                }
            }
        }
    },
   
    mounted() {       
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

b-button[disabled]{
  border: 1px solid #999999;
  background-color: #white;
  color: #666666;
}

  


</style>