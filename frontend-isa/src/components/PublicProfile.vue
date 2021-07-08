<template>
    <div id="homepage">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="../assets/nistagram.png">
</span>
               </div>
             <div style="float: left; margin: 15px;">  

                 <button  class="btn btn-primary btn-lg"  style="width:200px;margin-top:60px;margin-left:1250px;" id="btnSignIn" 
            v-on:click = "welcome"> Sign in/Sign up</button>             
       

        <div  style="height: 80px;">

        <span  style="float:left;margin:15px">
                    
          <div class="input-group mb-3">
              <input type="text" v-model="profile" class="form-control" placeholder="Enter username" aria-label="Enter username" aria-describedby="addon-wrapping">
              <div class="input-group-append">
                  <button class="btn btn-info" style="background: #474A8A;color:white" type="button"  v-on:click = "searchProfile(profile)" >Search</button>
                </div>
           </div>
      </span>
      <span  style="float:left;margin:15px">
                    
          <div class="input-group mb-3">
              <input type="text" v-model="location" class="form-control" placeholder="Enter location" aria-label="Enter location" aria-describedby="addon-wrapping">
              <div class="input-group-append">
                  <button class="btn btn-info" style="background: #474A8A;color:white" type="button"  v-on:click = "searchLocation(location)" >Search</button>
                </div>
           </div>
      </span>
      <span  style="float:left;margin:15px">
                    
          <div class="input-group mb-3">
              <input type="text" v-model="tag" class="form-control" placeholder="Enter tag" aria-label="Enter tag" aria-describedby="addon-wrapping">
              <div class="input-group-append">
                  <button class="btn btn-info" style="background: #474A8A;color:white" type="button"  v-on:click = "searchTag(tag)" >Search</button>
                </div>
           </div>
      </span>
</div>
            
            
            
                 








<!-- pretraga profila!-->
<div style="float: left; margin: 15px;" v-if="pregled1">  
         <!--FRIEND'S POSTS-->
             <b-card class="post_look" v-for="post in pretraga1" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
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


<!-- pretraga lokacije !-->
<div style="float: left; margin: 15px;" v-if="pregled2">  
         <!--FRIEND'S POSTS-->
             <b-card class="post_look" v-for="post in pretraga2" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
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



<!-- pretraga taga !-->
<div style="float: left; margin: 15px;" v-if="pregled3">  
             <b-card class="post_look" v-for="post in pretraga3" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
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


         <!--FRIEND'S POSTS-->
         <div v-if="postovi">
                     <h3 style="color: #0D184F;font-size:35px;margin-top:60px;margin-left:5px;">Posts of public profiles</h3>

             <b-card class="post_look"  v-for="post in posts" v-bind:key="post.fileName">
                  <b-row >
                        <h4 align="left"><b-icon icon="person" aria-hidden="true"></b-icon>  {{post.username}}</h4>
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
                

<section>
                <b-button 
                variant="outline-secondary"  
                v-on:click = "allComment(post)"
                style="margin-top: 0% !important;
                margin-right: 88%;
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
       <label style="margin-left:-500px;font-size:28px;color:black">All comments</label>

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
    </div>
</template>

<script>
export default {
    name: 'Homepage',
    data() {
    return {
        
        posts: [],
        pretraga1: [],
        comments: [],
        pretraga2: [],
        pretraga3: [],
        usernameTo:'',
        usernameFrom:'',
        videoText: "mp4",
        //likesNumber:0,
        numberOfLikes:0,
        numberOfDislikes:0,
        loggedUser: {} ,
        postovi:true,
        bp:false,
        tag: null,
        location: null,
        profile: null,
        pregled1:false,
        pregled2:false,
        pregled3:false
       
        }
    },
    async mounted(){
        
            this.axios.get('/media/post/getPostPublic')
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
        allComment: function(post){
            //alert("idemooo");
            //alert("logovani komentarise "+this.loggedUser.username);
            //alert("komentar "+this.comment);
            //alert("kome komentarise sliku "+post.username);
            //alert("id posta "+post.idPost);
          this.bp = true;
          this.postovi = false;
                      




            this.axios.get('/media/post/getComments/'+post.idPost,{ 
                }).then(response => {
                    //alert("prikaziii");
                    this.comments = response.data;
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },




     back: function(){
         this.postovi = true;
          this.bp = false;
      },
    searchLocation: function(location){
          this.location= location

          this.axios.get('/media/post/searchPublicProfileByLocation/'+this.location,{
      }).then(response => {
                this.postovi = false;
                this.pregled2 = true;
                this.pregled1 = false;
                                this.pregled3 = false;


                
                this.pretraga2= response.data;
                let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                 for(let j=0; j< this.pretraga2[k].media.length; j++){
                    if(!this.pretraga2[k].media[j].fileName.includes(video)){
                                console.log("usao je u if");
                                this.pretraga2[k].media[j].imageByte = 'data:image/jpeg;base64,' + this.pretraga2[k].media[j].imageByte;
                            }else{
                                this.pretraga2[k].media[j].imageByte = 'data:video/mp4;base64,' + this.pretraga2[k].media[j].imageByte;       
                            }  
                            console.log("uslo");
                        }
                 }
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
        
                
      }
      ,
      searchTag: function(tag){
          this.tag= tag

          this.axios.get('/media/post/searchPublicProfileByTags/'+this.tag,{
      }).then(response => {
                this.postovi = false;
                this.pregled2 = false;
                this.pregled1 = false;
                this.pregled3 = true;


                
                this.pretraga3= response.data;
                let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                 for(let j=0; j< this.pretraga3[k].media.length; j++){
                    if(!this.pretraga3[k].media[j].fileName.includes(video)){
                                console.log("usao je u if");
                                this.pretraga3[k].media[j].imageByte = 'data:image/jpeg;base64,' + this.pretraga3[k].media[j].imageByte;
                            }else{
                                this.pretraga3[k].media[j].imageByte = 'data:video/mp4;base64,' + this.pretraga3[k].media[j].imageByte;       
                            }  
                            console.log("uslo");
                        }
                 }
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
        
                
      }
      ,




    searchProfile: function(profile){
          this.profile= profile

          this.axios.get('/media/post/searchPublicProfile/'+this.profile,{
      }).then(response => {
                this.postovi = false;
                this.pregled2 = false;
                this.pregled1 = true;
                 this.pregled3 = false;


                
                this.pretraga1= response.data;
                let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                 for(let j=0; j< this.pretraga1[k].media.length; j++){
                    if(!this.pretraga1[k].media[j].fileName.includes(video)){
                                console.log("usao je u if");
                                this.pretraga1[k].media[j].imageByte = 'data:image/jpeg;base64,' + this.pretraga1[k].media[j].imageByte;
                            }else{
                                this.pretraga1[k].media[j].imageByte = 'data:video/mp4;base64,' + this.pretraga1[k].media[j].imageByte;       
                            }  
                            console.log("uslo");
                        }
                 }
                
              
            }).catch(res => {
                        alert("greskaa");
                            console.log(res);
                    });
        
                
      }
      ,




        welcome : function(){
        window.location.href = "/Welcome";
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