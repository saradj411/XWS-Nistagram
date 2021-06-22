<template>
    <div>
    <h2>Admin profile </h2>
        
     <button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "request">See requests for verification </button>
    <button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "logOut"> Log out </button>
        
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