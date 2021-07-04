<template>
    <div id="addingStories">
       
       <form>
            <h4 style="left: 10px;">Choose image </h4>
            <input type="file" multiple name="image" accept="image/png, image/jpeg, video/mp4,video/x-m4v,video/*" id="file" ref="file" v-on:change="handleFileUpload()">
            <h4 for="textarea-large" 
            class="text-left" 
            style="margin-bottom:2%; 
            margin-left:20%;
                margin-top: 5% !important;">Description:</h4>
                <b-col sm="12">
                    <b-form-textarea
                    style=" 
                    margin-left:-10%;"

                    id="textarea-large"
                    v-model="description"
                    placeholder="type here.."
                    ></b-form-textarea>
                </b-col>
                <h4 
                style="margin-bottom:2%; 
                margin-left:20%;
                margin-top: 5% !important;">
                    Choose location
                </h4>
                <input 
                list="my-list-id" 
                v-model="selectedLocation" 
                class="input_style" 
                placeholder="enter location.."
                style="margin-top: 1% !important; width:500px; margin-left:-10%;">
                    <datalist id="my-list-id">
                        <option v-for="loc in locations" v-bind:key="loc.id">
                            {{ loc.location}}
                        </option>
                    </datalist>
                <h4 
                style="margin-bottom:2%; 
                margin-left:20%;
                margin-top: 5% !important;">
                    Add tags
                </h4>
                <b-form-tags
                    v-model="tags"
                    tag-variant="danger"
                    size="lg"
                    style="background: #999a8d;  margin-left:-10%;"
                    separator=" "
                    placeholder="Enter new tags separated by space"
                ></b-form-tags>
                
              <section>
                <b-button 
                variant="outline-secondary"  
                v-on:click = "saveMedia"
                style="margin-top: 10% !important;
                margin-left: 20%;
                color: #406b99;
                width: 200px;"
                >
                <b-icon icon="plus-circle" aria-hidden="true"></b-icon> 
                Share post
                </b-button>
                 </section>
  </form>
  </div>
</template>

<script>
export default {
    //name: 'AddingStories',
    data() {
    return {
        file: [],
        user:'',
        locations: [],
        selectedLocation:[''],
        description: "",
        tags: [''],
        fileName:'',
        medias:[],
        highlight:'',
        closeFriends:'',
         loggedUser: {} 
        }
    },
    mounted() {
       

            this.axios.get('/media/location/findAll',{ 
             headers: {
                 
             }
         }).then(response => {
              this.locations = response.data;
         }).catch(res => {
               alert(Error)
                console.log(res);
            });


    this.axios.get('/profile/api/users/getLoggedUser',{
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                       this.loggedUser = response.data;

          
        
                    }).catch(res => {                        
                                         
                        console.log(res.response);
                       
                    });   
     
           
         
           
        
    },
    methods:{
       
        saveMedia : function() {
           let formData = new FormData();
            
            for( var i = 0; i < this.$refs.file.files.length; i++ ){
                let file = this.$refs.file.files[i];
                formData.append('file', file);
            }
            console.log("FORM DATA"+formData);
            this.axios.post('/media/post/saveImage',formData,{
                headers: {
                   
                }
                }).then(response => {
                        this.fileNames = response.data
                       //alert(this.fileNames)
                       //alert("Success")
                       console.log(response.data)
                       this.postPicture();
                       
                  
                    })
                    .catch(response => {
                    console.log(response.data)
                    alert("Eror")
                    
                    });  
        },
        postPicture :function(){  
            //alert(this.tags)
            const storyInfo= {
                description : this.description,
                location : this.selectedLocation,
                tags : this.tags,
                //username: this.user.username,
                //userId: this.user.id,
                fileName : this.fileNames
                 }
          

          this.axios.post('/media/post/addNewPost/'+this.loggedUser.username,storyInfo,{ 
                }).then(response => {
                    alert("Post is shared!");

                    console.log(response);  
                      window.location.href = "/proba";              
                }).catch(res => {
                    console.log(storyInfo.fileNames);
                    alert("Error"+res.response.data.message);

                });
        },
         handleFileUpload(){
            for(let i=0; i< this.$refs.file.files.length; i++){
                let fileOne = this.$refs.file.files[i];
                this.file.push(fileOne);
            }
         },

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
        margin-left: 190%;
        margin-top: -8%;
    }
    form{
        margin-left: 60px;
        width: 500px;
    }
    #addingStories {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: left;
        color: #692d5a;
        margin: auto;
        margin-top: 40px;
        margin-bottom: 40px;
        width: 40%;
        border: 4px solid #692d5a;
        padding: 40px;
        }
</style>