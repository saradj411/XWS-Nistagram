  <template>
  <div style="background-size: 150% 111%;  height: 1100px">
                     <label style="color:#474A8A;font-size:35px;" align = "center">Update profile</label>

      

<div style="margin: auto; width: 50%;margin-top:45px;">
            
    <form>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">Name:</label>
                        <input type="text" class="form-control" v-model = "user.name" >
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">Surname:</label>
                        <input type="text"  class="form-control" v-model = "user.surname">
                        </div>
                         <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">Email:</label>
                        <input type="text"  class="form-control" v-model = "user.email">
                        </div>
                         <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">Phone number:</label>
                        <input type="text"  class="form-control" v-model= "user.phone" >
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">Country:</label>
                        <input type="text"  class="form-control" v-model="user.country" >
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">City:</label>
                        <input type="text" class="form-control" v-model="user.city" >
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:#474A8A;font-size:22px;">Address:</label>
                        <input type="text"  class="form-control" v-model="user.address">
                        </div>
                        <div class="form-row">
                           
                         
                        

                       </div>
                         <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">Current Password:</label>
                            <input type="password" class="form-control" v-model="currentPassword" placeholder="Current password">
                        </div>
                    </div>
                    
           
                       <div class="form-row">

                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">New password:</label>
                            <input type="password" class="form-control" v-model="newPassword" placeholder=" New password">
                        </div>
                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">Repeat new password:</label>
                            <input type="password" class="form-control" v-model="newPasswordRepeat" placeholder="Repeat new password">
                        </div>
                        
                       </div>
                       <div class="form-row">

                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">New username:</label>
                            <input type="text" class="form-control" v-model="usernamee" placeholder=" New username">
                        </div>
                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">Biography:</label>
                            <input type="text" class="form-control" v-model="biography" placeholder="New biography">
                        </div>
                       </div>
                       <div class="form-row">

                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">Web site:</label>
                            <input type="text" class="form-control" v-model="webSite" placeholder=" New web site">
                        </div>
                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">New type of profile:</label>
                            <b-dropdown id="ddCommodity" style="color:#474A8A;font-size:22px;" name="ddCommodity" text="choose">
                                <b-dropdown-item  v-for="item in this.types" v-on:click ="typeIsSelected($event, item.categoryType)" v-bind:key="item.categoryType"> {{item.categoryType }}</b-dropdown-item>
                            </b-dropdown>       <span>{{this.choosenType}}</span>   
                       </div>
                       </div>

                       <div class="form-row">

                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">Message from unfollowes:</label>
                            <b-dropdown style="color:#474A8A;font-size:22px;" id="ddCommodity"  name="ddCommodity" text="choose">
                                <b-dropdown-item  v-for="item in this.messages" v-on:click ="typeIsSelected1($event, item.messagesType)" v-bind:key="item.messagesType"> {{item.messagesType }}</b-dropdown-item>
                            </b-dropdown> <span>{{this.choosenMessages}}</span>   
                        </div>
                        <div class="form-group  col-md-6">
                            <label style="color:#474A8A;font-size:22px;">Tag from unfollowers:</label>
<b-dropdown style="color:#474A8A;font-size:22px;" id="ddCommodity"  name="ddCommodity" text="choose">
                                <b-dropdown-item  v-for="item in this.tags" v-on:click ="typeIsSelected2($event, item.tagsType)" v-bind:key="item.tagsType"> {{item.tagsType }}</b-dropdown-item>
                            </b-dropdown>           <span>{{this.choosenTags}}</span>              </div>
                       </div>

                         
              
                    <button class="btn btn-primary btn-lg" v-on:click = "update">Change</button>
                   
                </form>


</div>

  

    </div>
</template>

<script>
export default {
  data() {
    return {
     types: [
          { categoryType: 'PRIVATE' },
          { categoryType: 'PUBLIC' }
      ],
      messages: [
          { messagesType: 'ENABLE' },
          { messagesType: 'DISABLE' }
      ],
      tags: [
          { tagsType: 'ENABLE' },
          { tagsType: 'DISABLE' }
      ],
    user: {},
    user1:{},
   
    name : "",
    id:"",
        surname : "",
        email : "",
        password : "",
        phone : "",
        city : "",
        country : "",
        currentPassword : "",
        newPassword : "",
        newPasswordRepeat : "",
        sifra:"",
        usernamee:"",
        newUsername:"",
        oldUsername:"",
        categoryType : "",
        choosenType : ""   ,        
        messagesType : "",
        choosenMessages: ""   ,      
        biography:"",
webSite:"",
         
        tagsType : "",
        choosenTags: "" ,


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
    typeIsSelected : function(event, type) { 
           this.choosenType = type;
      },
      typeIsSelected1 : function(event, type) { 
           this.choosenMessages = type;
      },
      typeIsSelected2 : function(event, type) { 
           this.choosenTags = type;
      },
    korisnik: function(){
 this.axios.get('profile/api/users/findOneById/'+this.user1.id,{ 
             
         }).then(response => {
               this.user=response.data;
         }).catch(res => {
                       alert("ne valja.");
                       console.log(res);
                 });
    },
     update: function(){
        if(this.newPassword != this.newPasswordRepeat) {
            alert("New passwords must be equal.")
            return;
        }else{
        
        if(this.currentPassword ==""){
          
          this.sifra=this.user.password
        }else if (this.currentPassword !=this.user.password) {
           alert("Invalid old password")
            return;
        } else if (this.newPassword!=="") {
           this.sifra=this.newPassword
            }else{
          this.sifra=this.user.password
        }

        if(this.usernamee==""){
          this.newUsername=this.user.username
        }else{
           this.newUsername=this.usernamee
        }
if(this.choosenType=='PUBLIC'){
                this.privateProfile=false;
            }
            
            if(this.choosenType=='PRIVATE'){
                this.privateProfile=true;
            }



            if(this.choosenMessages=='ENABLE'){
                this.messageFromUnfollowes=true;
            }
            
            if(this.choosenMessages=='DISABLE'){
                this.messageFromUnfollowes=false;
            }


            if(this.choosenTags=='ENABLE'){
                this.tagFromUnfollowers=true;
            }
            
            if(this.choosenTags=='DISABLE'){
                this.tagFromUnfollowers=false;
            }
        //alert(this.user.id)
        //alert(this.user.username)
        //alert(this.usernamee)
           const info = {
                    id : this.user.id,
                    name: this.user.name,
                    surname : this.user.surname,
                    phone: this.user.phone,
                    address: this.user.address,
                    city:this.user.city,
                    country:this.user.country,
                    password: this.sifra,
                    email:this.user.email,
                    oldUsername:this.user.username,
                    username:this.newUsername,
                    privateProfil:this.privateProfile,
                    messageFromUnfollowes:this.messageFromUnfollowes,
                    tagFromUnfollowers:this.tagFromUnfollowers,
                    biography:this.biography,
                    webSite:this.webSite




                    
          }
    this.axios.post('/profile/api/users/updateUser',info,{
      }).then(response => {
               this.nesto=response.data;
                alert("Changes have been saved!");
                  window.location.href = "/profile";
          }).catch(res => {
                       //alert("Please first choose allergy!");
                      
                       console.log(res);
                 });
      }
     }
}
}
</script>

  