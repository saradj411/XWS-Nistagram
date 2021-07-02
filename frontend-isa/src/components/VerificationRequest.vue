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
                        <div class="form-group col-md-6">
                        <label style="color: #0D184F;font-size:22px;">Choose image:</label>
                        </div>
                        <div class="form-group col-md-6">
                     <button class="btn btn-primary btn-lg"  v-on:click = "update" style="background:#474A8A;width:130px;">Choose</button>

                        </div>
                        
                    </div>
                  
                     
                    <button class="btn btn-primary btn-lg" v-on:click = "update" style="width:130px;">Sent</button>

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
    methods:
    
    {
     update: function(){
      
       
    const adminInfo = {
                    name : this.user.name,
                    lastname: this.user.surname,
                    category: this.choosenType,
                    username:this.user.username

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
      }
      ,typeIsSelected : function(event, type) { 
           this.choosenType = type;
      }
}
   
}

</script>


















