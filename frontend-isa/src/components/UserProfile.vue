<template>
    <div>

    <h2>Profile of {{loggedUser.name}} {{loggedUser.surname}} </h2>
         <h2>Category {{loggedUser.category}}  </h2>
    
       <span  style="float:right;margin:1px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
  
      <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "post">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Posts</b-button>

      <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "addPosts">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
       <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "addStory">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add story</b-button>
       <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "update">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Update profile</b-button>
       <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "request">
                    <b-icon icon="tools" aria-hidden="true"></b-icon>Sent a request for verification </b-button>
 <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "favorites">
                    <b-icon icon="heart-fill" aria-hidden="true"></b-icon>Favorites</b-button>



        
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