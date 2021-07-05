<template>

  <div >
                  <h3 style="font-size:35px;">Reported unappropriate content</h3>



<!--prikaz -->
<div >

    <div style=" width:650px;margin-left:38px;margin-top:60px;"  v-for="post in this.posts"  v-bind:key="post.fileName">
         <form>
             <div style="float: left; margin: 15px;">  
         <!--FRIEND'S POSTS-->
             <b-card >
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
             <h5 v-if="numberOfLikes==0"  align="left"><b-icon style="color: black" icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}} likes </h5>
              <h5 v-if="numberOfLikes!=0" align="left"><b-icon style="color: black" icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{numberOfLikes}} likes </h5>
            <h5 v-if="numberOfDislikes==0" align="left"> <b-icon style="color: black" icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
            </h5>
            <h5 v-if="numberOfDislikes!=0" align="left"> <b-icon style="color: black" icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
            </h5>
            
            
            <h5 align="left"> <b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveInFavorites($event,post)"></b-icon></h5>
               
        </b-card>
        
        </div> 
           <table style="background:#B0B3D6;" id="table2" class="table" >

              <tbody>
                  
      
    <tr style="font-size:22px;">
      <th></th>
      <td >Number Of Inappropriate Vote:</td>   
       <td>{{post.numberOfInappropriateVote}}</td>
    
    </tr>
    

    
  </tbody>
                        </table>

                        <button class="btn btn-primary btn-lg"  style="margin-left:30px; margin-top:42px;background:#474A8A">Remove content</button>
                        <button class="btn btn-primary btn-lg"  style="margin-left:30px; margin-top:42px;background:#474A8A">Remove profile</button>


                </form>
      
      
        </div>
        </div>
</div>

</template>

<script>
export default {
  data() {
    return {
      
     id : this.$route.params.id,
       dermatolog : {},
       jeste:"",
         name : "",
     dermatologistName:"",
     dermatologistSurname:"",
     dermatologists:"",
     showDermatologists : true,
     showDermatologistsTable : false,
     posts:{},
      choosen:"",
      jedanDermtolog:{},
      showNew:false,
      date:null,
       end:null,
       start:null,
       price:null

       
    }
  },
  mounted() {
        this.axios.get('/media/post/getInappropriatePost')
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

         }).catch(res => {
                console.log(res);
        });    
        
        
    }
     ,
 methods:{

   

      

      
      },
      
      }
   


</script>

























