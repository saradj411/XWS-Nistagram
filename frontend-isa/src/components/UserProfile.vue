<template>
    <div>
       <span  style="float:right;margin:1px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
  
      <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "post">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Posts</b-button>

      <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "addPosts">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
       <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "update">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Update profile</b-button>
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