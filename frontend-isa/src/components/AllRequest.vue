<template>

  <div >
                  <h3 style="color: #0D184F;font-size:35px;">List of verification requests</h3>



<!--prikaz -->
<div >

    <div style=" width:650px;margin-left:38px;margin-top:60px;"  v-for="d in this.pharmacies"  v-bind:key="d.id">
           <div>
                    <b-img v-if="!d.image.includes(videoText)" thumbnail  v-bind:src="d.imageByte" width="380" height="380" alt="Image 1"></b-img>
                             <video v-if="d.image.includes(videoText)" autoplay controls v-bind:src="d.imageByte" width="300" height="300" style="display:block; margin-left:auto; margin-right:auto"></video>

                 </div> 
                 
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
    <tr>
       <th></th>
       
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
       price:null,
       videoText: "mp4",
       


       
    }
  },
  mounted() {
        this.axios.get('/profile/request/findAll/')
        .then(response => {
                this.pharmacies = response.data;  

                  let video = "mp4";
                for(let k=0; k< response.data.length; k++){
                   if(!this.pharmacies[k].image.includes(video)){
                                console.log("usao je u if");
                                this.pharmacies[k].imageByte = 'data:image/jpeg;base64,' + this.pharmacies[k].imageByte;
                            }else{
                                this.pharmacies[k].imageByte = 'data:video/mp4;base64,' + this.pharmacies[k].imageByte;       
                            }  
                            console.log("uslo");
                        
                 }

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

























