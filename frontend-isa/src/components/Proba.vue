<template>
    <div id="homepage">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="../assets/nistagram.png">
               
         <!--FRIEND'S POSTS-->
             <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                 <div v-for="m in post.media" v-bind:key="m.imageBytes">
                    <b-img v-if="!m.fileName.includes(videoText)" thumbnail  v-bind:src="m.imageByte" alt="Image 1"></b-img>
                             <video v-if="m.fileName.includes(videoText)" autoplay controls v-bind:src="m.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div>      
                      
                      
        </b-card>
         </span> 
        </div> 
    </div>
</template>

<script>
export default {
    name: 'Homepage',
    data() {
    return {
        
        posts: [],
        
        videoText: "mp4",
       
        }
    },
    async mounted(){
     
            this.axios.get('/media/post/getPostByUsername/saki')
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
        width: 30%;
        height: 80%;
        margin-left: 20%;
        margin-bottom: 4%;
        margin-top: 2%;
    }
</style>