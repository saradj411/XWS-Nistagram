<template>
    <div class="home">

      <img src="../assets/nistagram.png" width="300" height="250" style="position: relative; margin: 0 auto; margin-top: 60px;"> 
      <table style=" margin-top: 20px; width: 50%;" >
        <tr>
          <td style="color: black;"> Username: </td>
        </tr>
        <tr>
          <td><input type="text " 
            placeholder="Enter username.." class="form-control form-rounded"  style=" margin-top: 20px; " v-model="username"> </td>
        </tr>
        <tr>
          <td ><label style="margin-top: 10px;  color: black;"  >Password:</label>  </td>
        </tr>
        <tr>          
          <td><input type="password"  style="margin-top: 10px;  " placeholder="Enter password.." class="form-control form-rounded" v-model="password" > </td>
        </tr>
        <tr>
          <td>
            <button class="btn btn-dark" id="btnSignIn" style=" margin-top: 20px; width: 80%;" :disabled="!username || !password" 
            v-on:click = "signIn"> Sign in</button>
          </td>
        </tr>
        <tr>
          <td>
            <label style="color: black; margin-top: 20px; font-size: 15px;"  >You dont have account? </label>
          </td>
          </tr>
          <tr>
          <td>
            <button class="btn btn-dark" id="btnRegister" style=" margin-top: 0px; width: 80%;" v-on:click = "registrationForm"> Registration </button>
          </td>
        </tr>

        
      
      </table>
      
      <br>
     
  </div>

</template>



<script>
export default {
  data() {
    return {
      username:"",
      password:"",
       pharmacies : [],
       pharmacies1 : [],
       drugs:[],

       showTable: true,
       showDrugTable: false,

       showSearchPharmacyTable:false,
       showSearchDrugTable:false,

       pharmacyName: null,
       pharmacyCity: null,

       showSearchPharmacy:true,
       showSearchDrug:false
      
       
    }
  },
  methods:{
   
        registrationForm : function(){
        window.location.href = "/registration";
      },
      loginForm : function(){
        window.location.href = "/login";
      },
      update : function(){
        window.location.href = "/UpdateAccount";
      },
    
      signIn: function(){
        const loginInfo = 
                {
                    username: this.username,
                    password: this.password
                }
                this.axios.post('/profile/api/users/login', loginInfo, {
                    headers: 
                    {          
                         
                        
                    }}).then(response => 
                    {                        
                        console.log(response.data);
                        if(response.data.role === "USER")
                        {
                          console.log("USER");
                          
                          window.location.href = "/profile";


                        }else if(response.data.role === "ADMIN"){
                            window.location.href = "/AdminProfile";
                        }
                               
                    }).catch(res => { 
                        if(res.response.status === 400)
                           alert("Wrong password or email.");
                        
                        console.log(res.response);
                        this.errorMessage = res.response.data.message;
                       
                    });    
              
                }
    
},
mounted() {
      
       this.axios.post('/media/story/setVisibleStory')
        .catch(res => {
                alert("Nesto ne valja");
                console.log(res);
        });

        
    }
     
}
</script>

<style>
body {
  margin: 0;
  padding: 0;
  height: 100vh;
}
.input--error
    {
    border-color:red;
    }
.home
{
  margin: 0 auto;
  
  width: 100%;

}

table
{

  width: 80%;
  margin: 0 auto;
  margin-top: 50px;
}



.btn:hover
{
  background-color: gray;
}

.form-rounded {
  border-radius: 30px 30px 30px 30px;
}

</style>
