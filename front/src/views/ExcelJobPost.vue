<template>
    <div class="jobposting">
      <!-- 기존 게시판 코드 -->
      <div>
         <b-container fluid>
           <!-- 게시판 헤더 -->
           <b-row style="padding:10px;">
             <!-- 게시판 제목 -->
             <b-col md="6">
               <h2 align="left">채용 공고 리스트</h2>
             </b-col>
            </b-row>
           <!-- 고객사 목록 table -->
            <b-row>
               <b-table hover :items="contents" :filter="filter" :fields="fields" @row-clicked="JobDetail"></b-table>
            </b-row>
         </b-container>
      </div>
    </div>
  </template>
  
  <script>
  import http from "../http-common";
  
  export default {
    name: 'ExcelJobPost',
    data(){
      return{
        contents:[],
        fields:[
          {key:'comp_id', label:'No.'},
          {key:'b', label:'구인 공고문 내용'},
        ],
        filter: '',
        user_code:'asd'
      }
    },
    methods:{
      retrieveContents(){
        http
          .get("/get-comp-job-list")
          .then(response=>{
            this.contents = response.data;
            console.log(response.data);
          })
          .catch(e=>{
            console.log(e);
          })
      },
      JobDetail(row)
      {
        this.$router.push({ name: 'CandidateMatching', params: { comp_id: row.comp_id }});
      }
    }
    
    ,
    mounted(){
      this.retrieveContents();
    }
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .hello{
    padding: 30px
  }
  </style>