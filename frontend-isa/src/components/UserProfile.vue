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
                    <img src="../assets/logo.png" width="100px" style="margin: 10px; margin-top:20px;" height="100px">
                </div>           
                <p>{{loggedUser.name}} {{loggedUser.surname}} </p>
                <p><i> @{{loggedUser.username}}</i></p>
                <p><i>Bio: {{loggedUser.getBiography}}</i></p>
                <p><i>Category: {{loggedUser.category}}</i></p>
                
            </div>
            
            <b-button class="btn btn-dark" style=" margin-top: 0px; width: 80%;" v-on:click = "update"><b-icon icon="person" aria-hidden="true"></b-icon> Update </b-button>
            <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "request">
                <b-icon icon="tools" aria-hidden="true"></b-icon> Sent a request for verification </b-button>

                <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "post">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Posts</b-button>   
                    <b-button class="btn btn-dark" style=" margin-top: 10px; width: 80%;" v-on:click = "addPosts">
                        <b-icon icon="camera" aria-hidden="true"></b-icon> Add post</b-button>   
            <button class="btn btn-dark" style=" margin-top: 10px; width: 80%; " v-on:click = "logOut"> Log out </button>
           
            
        </div> 
           
			<div class="fotos" v-bind:class="[!visible ? 'show' : 'hide', 'shadow-lg']" >
            <div>
                <h1> Fotos : </h1>
            </div>
        </div>  
        
        <div class="suggestions"  v-bind:class="[visible ? 'show' : 'hide', 'shadow-lg']">
            <div style="width: 100%;">
                <h1 style="margin-top: 10px;"> Searh profile: </h1>
                <button type="button" class="close  btn pull-right"  @click="changeVisibilityFalse" aria-label="Close">
                    <span aria-hidden="true"> X </span>                    
                  </button>
                  <div v-for="user in this.searchUsername" class="userDiv" v-bind:key="user.username">
                   @{{user.username}} - {{user.name}} {{user.surname}}

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
       


        visible: false
    }
  },
  computed:{

    computedVisibility: function() {
            return this.visibility;
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
      post: function(){
        window.location.href = "/Proba";
      },     
      searchFunction: function()  
      {
          console.log(this.search);
          document.getElementsByClassName('suggestions').style.visibility = 'visible';          
          document.getElementsByClassName('fotos').style.visibility = 'hidden';
            
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
    height: 100%;
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
width: 80%;
height: 100%;
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
height: 100%;
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
</style>