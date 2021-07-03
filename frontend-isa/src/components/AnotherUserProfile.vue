<template>
<div class="profile">
    <h1 style="color:white;"> {{this.userProfile[0].name}} {{this.userProfile[0].surname }} </h1>
    <p><i> @{{ this.userProfile[0].username }} </i></p>
    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%; color:white;"  
    :disabled="this.exists" 
    :style="this.exists ? {'background-color': 'green'} : ''"
    v-on:click = "follow()"
     >
                            <b-icon icon="plus" aria-hidden="true"></b-icon> {{ text }} </b-button> 



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
            followingStatus: false,
            requestStatus: false,
            following: []

        }
      },
      methods:{
        follow: function(){
            const userForFollow = 
            {
                username: this.userProfile[0].username
            }

            if(this.userProfile[0].privateProfil==true)
            {
                // salji zhatev
                if(this.requestStatus == false)
                {
                    //nema poslatog zahteva
                    this.axios.post('/profile/api/profile/addRequest4follow',userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.exists = true;
                                this.requestStatus = true;
                                this.text = " Request sent ";
                            }).catch(res => {
                                        console.log("neushep");
                                        console.log(res.response.data.message);
                                    });
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
                                                        this.exists = false;
                                                        this.requestStatus = false;
                                                        this.text = " Follow ";
                                                    }).catch(res => {
                                                                console.log("neushep");
                                                                console.log(res.response.data.message);
                                                            });


                }

            }else
            {
                //zaprati
            }

           
           /*if(this.requestStatus == false)
                {
                    this.axios.post('/profile/api/profile/addRequest4follow',userForFollow ,{ 
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8' 
                                }
                            }).then(response => {
                                console.log(response.data);
                                this.exists = true;
                                this.text = "Request sent";
                            }).catch(res => {
                                        console.log("neushep");
                                        console.log(res.response.data.message);
                                    });
                }
                else
                {
                    //brisanje poslatog zahteva
                }*/
           
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
                  console.log("Uspeh");
                  console.log(response.data[0]);
            }).catch(res => {
                           console.log("neushep");
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
                                        {     console.log("getRe")
                                             this.sendedRequest = response.data;
                                             
                                             for(var u in this.sendedRequest)
                                             { 
                                                 console.log(this.sendedRequest[u].username);
                                                 console.log(this.userProfile[0].username);

                                                 if(this.sendedRequest[u].username == this.userProfile[0].username)
                                                 {
                                                     console.log("USAO")
                                                     this.exists = true;
                                                     this.requestStatus = true;
                                                     this.text = "Request sent";
                                                     break;
                                                 }
                                             }
                                             
                                             //console.log("PRONADJENI ZAHTEV"); 
                                             //console.log(this.sendedRequest);   
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
                                             
                                             //console.log("PRONADJENI ZAHTEV"); 
                                             //console.log(this.sendedRequest);   
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
    height: 1000px;
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