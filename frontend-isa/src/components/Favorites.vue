<template>
    <div id="homepage">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="../assets/nistagram.png">

                 <b-button  class="btn btn-secondary btn-lg" style="margin-left:20px;" v-on:click = "homePage">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Home </b-button>
</span>

            <span  style="float:right;margin:20px">
                    <b-button class="btn btn-secondary btn-lg" style="margin-right:20px;" v-on:click = "addNewCollecion">
                        <b-icon icon="plus-square" aria-hidden="true"></b-icon> Add new collection</b-button>
                </span>
                
               </div>
                <div   v-if="showAdd" style="float:right;margin-top:30px;margin-right:10px">
           <h4 for="textarea-large" 
            class="text-left" 
            style="margin-bottom:2%; 
            margin-left:20%;
                margin-top: 5% !important;">Name:</h4>
                <b-col sm="12">
                    <b-form-textarea
                    style=" 
                    margin-left:-10%;"

                    id="textarea-large"
                    v-model="name"
                    placeholder="type here.."
                    ></b-form-textarea>
                </b-col>
                    <b-button class="btn btn-secondary btn-lg" style="margin:10px;" v-on:click = "addCollecion">
                        <b-icon icon="plus-square" aria-hidden="true"></b-icon> Add</b-button>
                </div>
             <div style="float: left; margin: 15px;">  

         <!--Favorites-->
             <b-card class="post_look" v-for="f in favorites" v-bind:key="f.idFavourites">
                  <b-row >
                        <h4 align="left"><b-icon style="font-size: 30px;margin-top:10px;" icon="bookmark" aria-hidden="true"></b-icon></h4>
                        <td><router-link :to="{ path: '/CollectionPosts/'+f.idFavourites}" v-slot="{href, navigate}" custom>
           <b-link style="font-size: 30px;margin-bottom:10px;" :href="href" @click="navigate"  elevation="1">
              {{f.name}}
            </b-link >
         </router-link></td>
                        </b-row>
                       
                 <div>
                    <b-img v-if="!f.fileName.includes(videoText)" thumbnail  v-bind:src="f.imageByte" alt="Image 1"></b-img>
                             <video v-if="f.fileName.includes(videoText)" autoplay controls v-bind:src="f.imageByte" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div>      
                
            
        </b-card>
        
        </div> 
    </div>
</template>

<script>
export default {
    name: 'Homepage',
    data() {
    return {
        
        favorites: [],
        usernameTo:'',
        usernameFrom:'',
        videoText: "mp4",
        //likesNumber:0,
        numberOfLikes:0,
        numberOfDislikes:0,
        loggedUser: {} ,
        name: "",
        rez:"",
        showAdd:false
       
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
     
           
         
                
   },
    methods:{
        homePage: function(){
           window.location.href = "/profile";
        },
        addCollecion: function() {
          
            this.axios.post('/media/favorites/addNewCollection/'+this.loggedUser.username+"/"+this.name,{
                headers: {
                   
                }
                }).then(response => {
                       this.rez = response.data;
                       alert("Success");
                       this.showAdd=false;
                    window.location.href = "/Favorites";
                       
                       
                  
                    })
                    .catch(response => {
                    console.log(response.data)
                    alert("Eror")
                    
                    });  
        },
        addNewCollecion:function(){
            this.showAdd=true;
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