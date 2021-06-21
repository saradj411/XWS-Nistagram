<template>
    <div>
    <button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "logOut"> Log out </button>
     <button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "update"> Update </button>
<button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "post"> Update </button>


      <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "addPosts">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
        <h2> User profile:  {{loggedUser.name}} {{loggedUser.surname}} </h2>
        
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
        addPosts: function(){
        window.location.href = "/AddPost";
      },
      post: function(){
        window.location.href = "/Proba";
      },     
    

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