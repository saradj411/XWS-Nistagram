 <template>
  <div >

<div style="margin: auto; width: 50%;margin-top:45px;">
            <h3 style="color: #0D184F;font-size:35px;">Request for verification</h3>
            
    <form>   
                   <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color: #0D184F;font-size:22px;">Choose category:</label>
                        </div>
                        <div class="form-group col-md-6">
 <b-dropdown id="ddCommodity"  name="ddCommodity" text="Category list">
                                <b-dropdown-item  v-for="item in this.types" v-on:click ="typeIsSelected($event, item.categoryType)" v-bind:key="item.categoryType"> {{item.categoryType }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                        
                    </div>

                     <div class="form-row">
                          <h4 style="left: 10px;">Choose image </h4>
                            <input type="file" multiple name="image" accept="image/png, image/jpeg, video/mp4,video/x-m4v,video/*" id="file" ref="file" v-on:change="handleFileUpload()">
            
                        
                    </div>
                  
                     
                    <button class="btn btn-primary btn-lg" v-on:click = "saveMedia" style="width:130px;">Sent</button>

                </form>



</div>

  

    </div>
</template>

<script>
export default {
  data() {
    return {
       types: [
          { categoryType: 'INFLUENCFE' },
          { categoryType: 'BUSINESS' },
          { categoryType: 'MEDIA' },
          { categoryType: 'CARTON' },
          { categoryType: 'BRAND' },
          { categoryType: 'SPORT' },
          { categoryType: 'ORGANIZATION' },
      ],

      
       id : this.$route.params.id,
       drug : {},
       nesto:{},

    name : "",
   
    categoryType : "",
    lastname:"",
    username:"",
    choosenType : ""   ,
    user:"",
    category:""
 
       
    }
  },
  mounted() {
      this.axios.get('/profile/api/users/getLoggedUser',{
                    headers: 
                    {          
                        
                    }}).then(response => 
                    {                        
                       this.user= response.data;
                      
                               
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
            this.axios.post('/profile/request/saveImage',formData,{
                headers: {
                }
                }).then(response => {
                        this.fileNames = response.data
                       //alert(this.fileNames)
                       //alert("Success")
                       console.log(response.data)
                       this.sendRequest();
                       
                  
                    })
                    .catch(response => {
                    console.log(response.data)
                    alert("Eror")
                    
                    });  
        },
    
     sendRequest: function(){
      
       
    const adminInfo = {
                    name : this.user.name,
                    lastname: this.user.surname,
                    category: this.choosenType,
                    username:this.user.username,
                    image:this.fileNames

        } 
        this.axios.post('/profile/request/create',adminInfo,{
      }).then(response => { 
               this.nesto=response.data;
                alert("Request send!");
                window.location.href = "/profile";
          }).catch(res => {
                      alert("neceeee!");

                       console.log(res);
                 });
      },
      typeIsSelected : function(event, type) { 
           this.choosenType = type;
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


















