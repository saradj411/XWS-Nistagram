<template>
    <div id="homepage">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="../assets/nistagram.png">
                <b-button  class="btn btn-secondary btn-lg" style="margin-left:20px;" v-on:click = "homePage">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Home </b-button>
                <b-button  class="btn btn-secondary btn-lg" style="margin-left:20px;" v-on:click = "favoritesButton">
                    <b-icon icon="heart" aria-hidden="true"></b-icon>Favorites </b-button>
</span>
               </div>
             <div style="float: left; margin: 15px;">  
         <!--FRIEND'S POSTS-->
             <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                         <h4 align="right" style="margin-left:200px">  <b-button class="btn btn-secondary btn-lg" v-on:click = "addNewCollecion">
                        <b-icon icon="plus-square" aria-hidden="true"></b-icon> Add in other collection</b-button></h4>
            <div v-if="showAdd" style="float:right;margin-top:2px;margin-left:300px">
           <h4 for="textarea-large" 
            class="text-left" 
            style="margin-bottom:2%; 
            margin-left:20%;
                margin-top: 5% !important;">Collection:</h4>
                <b-col sm="12">
                    <input 
                list="my-list-id" 
                v-model="selectedCollection" 
                class="input_style" 
                placeholder="enter collection.."
                style="margin-top: 1% !important; width:200px; margin-left:10%;">
                    <datalist id="my-list-id">
                        <option v-for="loc in favorites" v-bind:key="loc.idFavourites">
                            {{ loc.name}}
                        </option>
                    </datalist>
                </b-col>
                    <b-button class="btn btn-secondary btn-lg" style="margin:10px;"  @click="addCollection($event,selectedCollection,post) ">
                        <b-icon icon="plus-square" aria-hidden="true"></b-icon> Add</b-button>
                </div>
                  </b-row>
                   
             <h6 align="left">{{post.location}}</h6>
            
                
                 <div v-for="m in post.media" v-bind:key="m.imageBytes">
                    <b-img v-if="!m.fileName.includes(videoText)" thumbnail  v-bind:src="m.imageByte" alt="Image 1"></b-img>
                             <video v-if="m.fileName.includes(videoText)" autoplay controls v-bind:src="m.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div>      
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
    </div>
</template>

<script>
export default {
    data() {
    return {
        id : this.$route.params.id,
        posts: [],
        usernameTo:'',
        usernameFrom:'',
        videoText: "mp4",
        //likesNumber:0,
        numberOfLikes:0,
        numberOfDislikes:0,
        loggedUser: {} ,
        favorites:"",
        showAdd:false,
        selectedCollection:[''],
        rez:""
       
        }
    },
    async mounted(){
        this.axios.get('/profile/api/users/getLoggedUser',{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                       this.loggedUser = response.data;

                               //alert(this.loggedUser.username)
            this.axios.get('/media/favorites/findAllByProfile/'+this.loggedUser.username)
            .then(response => {
                this.favorites = response.data;
                let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                    if(!this.favorites[k].fileName.includes(video)){
                                console.log("usao je u if");
                                this.favorites[k].imageByte = 'data:image/jpeg;base64,' + this.favorites[k].imageByte;
                            }else{
                                this.favorites[k].imageByte = 'data:video/mp4;base64,' + this.favorites[k].imageByte;       
                            }  
                            console.log("uslo");
                        
                 }
                
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
            
        
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   
     
           this.axios.get('/media/post/getPostByCollection/'+this.id)
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
                /*for(let k=0; k< response.data.length; k++){
                   if(!this.posts[k].fileName.includes(video)){
                            console.log("usao je u if");
                            this.posts[k].imageByte = 'data:image/jpeg;base64,' + this.posts[k].imageByte;
                        }else{
                           this.posts[k].imageByte = 'data:video/mp4;base64,' + this.posts[k].imageByte;       
                        }  
                        console.log("uslo");
                       
                 }*/
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
         
                
   },
    methods:{
        homePage: function(){
           window.location.href = "/profile";
        },
        favoritesButton: function(){
           window.location.href = "/Favorites";
        },
        addNewCollecion:function(){
            this.showAdd=true;
        },
    saveInFavorites: async function(event,post){
        console.log(post)
         alert(this.loggedUser.username)
         alert(post.idPost)
            this.axios.post('media/favorites/saveInFavorites/'+this.loggedUser.username+"/"+post.idPost,{ 
                

                }).then(response => {
                    alert("Post saved in favorites!");
                    //this.likesNumber = response.data
                    //this.numberOfLikes = this.likesNumber
                     
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
                      window.location.href = "/CollectionPosts/"+this.id; 
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
                     window.location.href = "/CollectionPosts/"+this.id; 
                    console.log(response);                
                }).catch(res => {
                    alert("You have already liked this post");
                    console.log(res);

                });
         },
        addCollection: async function(event,collection,post){
         
            this.axios.post('media/favorites/saveInOtherCollection/'+this.loggedUser.username+"/"+post.idPost+"/"+collection,{ 
                }).then(response => {
                    alert("Picture is saved!");
                     this.showAdd=false;  
                    console.log(response);  
                                
                }).catch(res => {
                    alert("You have already saved this post");
                    console.log(res)

                });


        }
    
}
}
</script>

<style scoped>
    .image_style{
        height: 400px;
        width: 60%;
    }

    .homepage_style{
        background: #f5f1f4; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 90px;
    }
    .space_style{
        margin-right:15px;
        margin-left:10px;
    }
    .object_space {
        width: 500px;
        margin-top: 1% !important;
        margin-left: 4% !important;
        margin-bottom: 2% !important;
    }
    .content_surface{
        background: #cac6c9; 
        width: 60%;
        height: 100%;
        margin-left: 20%;

    }
    .serach_look{

        margin-left: 170%;
        margin-top: -8%;
    }
    .serach_look1{
        

        margin-left: 138%;
        width: 20%;

        margin-top: -8%;
    }
    .post_look {
        background: #e4e4e4; 
        width: 60%;
        height: 80%;
        margin-left: 20%;
        margin-bottom: 4%;
        margin-top: 2%;
    }
</style>