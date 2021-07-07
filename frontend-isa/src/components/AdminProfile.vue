<template>
        <div class="sideView">
    <h2>Admin profile </h2>
        <div>
                    <img src="../assets/gizmo.jpg" width="100px" style="margin: 10px; margin-top:20px;" height="100px">
                </div>
 <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "request">
                <b-icon icon="tools" aria-hidden="true"></b-icon>Requests for verification</b-button>



<b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "content">
                <b-icon icon="exclamation" aria-hidden="true"></b-icon>Reported unappropriate content</b-button>

<b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "logOut">
                <b-icon icon="exclamation" aria-hidden="true"></b-icon>Log out</b-button>

    </div>
</template>
<script>
export default {
  data() {
    return {   
        loggedUser: {} 
      
       
    }
  },
  methods:{   
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
        window.location.href = "/AllRequest";
      },
      content: function(){
        window.location.href = "/UnappropriateContent";
      }
                
    

      },
      
mounted() {
      
      this.axios.get('/profile/api/users/getLoggedUser',{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                       this.loggedUser = response.data;
                               
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   

        
    }
     
}


</script>
.sideView
{
    color: white;
    background-color: black;
    width: 25%;
    height: 125vh;
    position: absolute;
    margin-top: -60px;
}