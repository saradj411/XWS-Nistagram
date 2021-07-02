<template>

  <div >
                  <h3 style="color: #0D184F;font-size:35px;">List of verification requests</h3>



<!--prikaz -->
<div >

    <div style=" width:650px;margin-left:38px;margin-top:60px;"  v-for="d in this.pharmacies"  v-bind:key="d.id">
         <form>
           <table style="background:#B0B3D6;" id="table2" class="table" >

              <tbody>
      
    <tr style="font-size:22px;color:#0D184F;">
      <th></th>
      <td >Name:</td>   
       <td>{{d.name}}</td>
    
    </tr>
    
    <tr style="font-size:22px;color:#0D184F;">
      <th></th>
      <td >Surname:</td>   
       <td>{{d.lastname}}</td>
    </tr>

    
    
 
   
    <tr style="font-size:22px;color:#0D184F;">
      <th></th>
      <td >Category:</td>   
       <td>{{d.category}}</td>
    </tr>
    
  </tbody>
                        </table>

                        <button class="btn btn-primary btn-lg" v-on:click = "accept(d.category,d.username)"  style="margin-left:30px; margin-top:42px;background:#474A8A">Accept</button>
                        <button class="btn btn-primary btn-lg"  v-on:click = "refuse(d.username)" style="margin-left:30px; margin-top:42px;background:#474A8A">Refuse</button>


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
     pharmacies:{},
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
        this.axios.get('/profile/request/findAll/')
        .then(response => {
                this.pharmacies = response.data;  

         }).catch(res => {
                console.log(res);
        });    
        
        
    }
     ,
 methods:{

   accept: function(kat,u){
      console.log("ajdeeee"+this.u);
      console.log(kat);
      const adminInfo = {
                    username : u,
                    category: kat,
              
        } 
        this.axios.post('/profile/request/acceptRequest',adminInfo,{
      }).then(response => { 
               this.nesto=response.data;
                alert("Accepted!");
                 window.location.href = "/AdminProfile";
          }).catch(res => {
                      alert("neceeee!");

                       console.log(res);
                 });
      },

      refuse: function(u){
      
        this.axios.post('/profile/request/refuseRequest/'+u,{
      }).then(response => { 
               this.nesto=response.data;
                alert("Refused!");
                 window.location.href = "/AdminProfile";
          }).catch(res => {
                      alert("neceeee!");

                       console.log(res);
                 });
      },

      

      
      },
      
      }
   


</script>

























