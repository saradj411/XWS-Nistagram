  <template>
  <div style="background-size: 150% 111%;  height: 1100px">
                     <label style="color:#474A8A;font-size:35px;" align = "center">Update profile</label>

      

<div style="margin: auto; width: 50%;margin-top:45px;">
            
    <form>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Name:</label>
                        <input type="text" class="form-control" v-model = "user.name" >
                        </div>
                        <div class="form-group col-md-6">
                        <label>Surname:</label>
                        <input type="text"  class="form-control" v-model = "user.surname">
                        </div>
                         <div class="form-group col-md-6">
                        <label>Email:</label>
                        <input type="text"  class="form-control" v-model = "user.email">
                        </div>
                         <div class="form-group col-md-6">
                        <label>Phone number:</label>
                        <input type="text"  class="form-control" v-model= "user.phone" >
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Country:</label>
                        <input type="text"  class="form-control" v-model="user.country" >
                        </div>
                        <div class="form-group col-md-6">
                        <label>City:</label>
                        <input type="text" class="form-control" v-model="user.city" >
                        </div>
                        <div class="form-group col-md-6">
                        <label>Address:</label>
                        <input type="text"  class="form-control" v-model="user.address">
                        </div>
                        <div class="form-row">
                           
                         
                        

                       </div>
                         <div class="form-group  col-md-6">
                            <label>Current Password:</label>
                            <input type="password" class="form-control" v-model="currentPassword" placeholder="Current password">
                        </div>
                    </div>
                    
           
                       <div class="form-row">

                        <div class="form-group  col-md-6">
                            <label>New password:</label>
                            <input type="password" class="form-control" v-model="newPassword" placeholder=" New password">
                        </div>
                        <div class="form-group  col-md-6">
                            <label>Repeat new password:</label>
                            <input type="password" class="form-control" v-model="newPasswordRepeat" placeholder="Repeat new password">
                        </div>
                        
                       </div>

                       <div>
                           <div class="form-group col-md-6">
                        <label>New username:</label>
                        <input type="text"  class="form-control" v-model="usernamee" placeholder=" New username">
                        </div> 
                        
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

    user: {},
   
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
        oldUsername:""
    }
  },
  
  mounted() {
        this.axios.get('profile/api/users/findOneById/'+1,{ 
             
         }).then(response => {
               this.user=response.data;
         }).catch(res => {
                       alert("ne valja.");
                       console.log(res);
                 });
               
},
  methods:{
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
                    username:this.usernamee,
                    
          }
    this.axios.post('/profile/api/users/updateUser',info,{
      }).then(response => {
               this.nesto=response.data;
                alert("Changes have been saved!");
          }).catch(res => {
                       //alert("Please first choose allergy!");
                       console.log(res);
                 });
      }
     }
}
}
</script>

  