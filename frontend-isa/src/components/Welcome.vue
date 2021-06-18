<template>


  <div  id="registration" style="background-image: url(https://image.freepik.com/free-photo/pharmacists-showing-medicine-bottle-discussing-prescription-drug-pharmacy_8087-2630.jpg);background-repeat: no-repeat;
  background-size: 150% 111%;  height: 1100px">

   <div style="background: #B0B3D6; height: 70px; margin-top: 10px">

      <span  style="float:right;margin:15px">
           <button class="btn btn-info btn-xs" v-on:click = "loginForm">Login</button>
           <button class="btn btn-info btn-xs" style="margin:10px;" v-on:click = "registrationForm">Register</button>
      </span>

    </div>
        
    
            
        </div>

</template>



<script>
export default {
  data() {
    return {
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
      }
    
    
},
mounted() {
      
      

        
    }
     
}
</script>

<style>
</style>
