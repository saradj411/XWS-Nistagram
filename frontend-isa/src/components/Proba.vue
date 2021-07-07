<template>
    <div id="homepage">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="../assets/nistagram.png">
                 
                <b-button  class="btn btn-secondary btn-lg" v-on:click = "homePage">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Home </b-button>
</span>
               </div>

             <div style="float: left; margin: 15px;" v-if="postovi">  
         <!--FRIEND'S POSTS
             <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
-->
             <div style="float: left; margin: 15px;">  
         <!--my post !-->
             <b-card class="post_look" v-for="post in posts" v-bind:key="post.idPost">

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
                






                
<span  style="float:left;margin:15px">
                    
          <div class="input-group mb-3">
              <input type="text" style="width:500px;" v-model="comment" class="form-control" placeholder="Add comment" aria-label="Enter username" aria-describedby="addon-wrapping">
              <div class="input-group-append">
                  <b-button class="btn btn-info" style="background:gray;color:white;margine-left:-2%" v-on:click = "getComments(post)" type="button"  ><b-icon icon="plus-circle" aria-hidden="true"></b-icon> 
                Share</b-button>
                </div>
           </div>
      </span>




 <section>
                <b-button 
                variant="outline-secondary"  
                v-on:click = "allComment(post)"
                style="margin-top: 0% !important;
                margin-right: 83%;
                color: #406b99;
                width: 250px;
                hight: 500px;"
                                

                >
                <b-icon icon="chat-square" aria-hidden="chat-square"></b-icon> 
                See all comments
                </b-button>
                 </section>



        </b-card>
             </div>
        </div> 
          <!--FRIEND'S POSTS-->
         
 <div v-if="bp" style="background:lightgray;width:800px;">  
       <label style="font-size:28px;color:black;margin-top:60px;margin-left:30px;">All comments</label>

<div  style=" width:700px;margin-left:30px;margin-top:40px;"  v-for="d in this.comments"  v-bind:key="d.id">
      
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
</template>

<script>
export default {
    data() {
    return {
        
        posts: [],
        videoText: "mp4",
        //numberOfLikes:0,
        //likesNumber:0,
        numberOfLikes :0,
        numberOfDislikes:0,
        loggedUser: {},
        comment:'',
        comments:[],
        postId:null,
        postovi:true,

       
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
            this.axios.get('/media/post/getPostByUsername/'+this.loggedUser.username)
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
        
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   
     
           
         
                
   },
    methods:{

        homePage: function(){
           window.location.href = "/profile";
        },
         back: function(){
                     this.bp = false;

          this.postovi = true;
      },
allComment: function(post){
            //alert("idemooo");
            //alert("logovani komentarise "+this.loggedUser.username);
            //alert("komentar "+this.comment);
            //alert("kome komentarise sliku "+post.username);
            //alert("id posta "+post.idPost);
            this.postovi = false;
                        this.bp = true;


            this.axios.get('/media/post/getComments/'+post.idPost,{ 
                }).then(response => {
                    //alert("prikaziii");
                    this.comments = response.data;
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
      
        getComments: function(post){
            //alert("idemooo");
            //alert("logovani komentarise "+this.loggedUser.username);
            //alert("komentar "+this.comment);
            //alert("kome komentarise sliku "+post.username);
            //alert("id posta "+post.idPost);

            const postInfo = {
                usernameFrom : this.loggedUser.username,
                usernameTo:post.username,
                comment : this.comment,
                postId: post.idPost

            }
            this.axios.post('media/post/commentPost',postInfo,{ 
                }).then(response => {
                    alert("Comment is shared");
                    this.nesto = response.data;
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });

        },
    saveInFavorites: async function(event,post){
        console.log(post)
         //alert(this.loggedUser.username)
         //alert(post.idPost)
            this.axios.post('media/favorites/saveInFavorites/'+this.loggedUser.username+"/"+post.idPost,{ 
                

                }).then(response => {
                    alert("Post saved in favorites!");
                     
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
                      window.location.href = "/proba";   
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
                      window.location.href = "/proba";   
                    console.log(response);                
                }).catch(res => {
                    alert("You have already liked this post");
                    console.log(res.response.data.message);

                });


        },

        dislikes: function(event,post){
        
         
                    this.dislikesNumber = 77;
              console.log(post)


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