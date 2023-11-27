<template>
<div>
  <b-container fluid>
           <!-- 고객사 목록 table -->
    <b-row style="padding:10px;">
    <!-- 게시판 제목 -->
      <b-col md="6">
        <h2 align="left">후보자 리스트</h2>
      </b-col>
    </b-row>
  <div>
    <b-table striped hover :items="indvInfoList"  :fields="filteredFields" @row-clicked="handleRowClick">
      <template #cell(score)="data">
        {{ (parseFloat(data.value)).toFixed(2) + "%" }}
      </template>
    </b-table>
  </div>
  <b-modal v-model="showModal" :title="'후보자 상세정보'" @hide="handleModalClose">
    <ul>
      <li><strong>[ 이름 ]</strong> {{ modalData.c }}</li>
      <li><strong>[ 전화번호 ]</strong> {{ modalData.e }}</li>
      <li><strong>[ 이메일 ]</strong> {{ modalData.f }}</li>
      <li><strong>[ 학력 ]</strong> {{ modalData.g }}</li>
      <li><strong>[ 경력 ]</strong> {{ modalData.h }}</li>
      <li><strong>[ 전문분야 ]</strong> {{ modalData.i }}</li>
      <li><strong>[ 후보자 적합도 ]</strong> {{ modalData.score }}</li>
    </ul>
  </b-modal>
</b-container>
</div>    
  </template>
  
  <script>
  import http from "../http-common";
  import { BModal } from 'bootstrap-vue'
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap-vue/dist/bootstrap-vue.css'


  export default {
    name: 'CandidateMatching',
    components: {
      BModal,
    },

    data() {
      return {
        indvInfoList: [],
        fields: [
          // { key: 'index', label: '#'},
          { key: 'a', label: '일시' },
          { key: 'b', label: '포지션' },
          { key: 'c', label: '성명' },
          { key: 'd', label: '생년' },
          { key: 'e', label: '전화번호' },
          { key: 'f', label: '이메일' },
          { key: 'g', label: '학력' },
          { key: 'h', label: '경력' },
          { key: 'i', label: '전문분야' },
          { key: 'j', label: '진행사항' },
          { key: 'k', label: '입수경로' },
          { key: 'l', label: '이력서번호' },
          { key: 'm', label: '컨택담당자' },
          { key: 'n', label: '업무내용' },
          { key: 'o', label: '자격요건' },
          { key: 'p', label: '관련전공 및 경력' },
          { key: 'score', label: '후보자 적합도' },
          
        ],
        // 원하는 필드만 선택하여 표시
        visibleFields: ['c', 'e', 'f', 'score'],
        clickedRow: null, // 클릭한 행의 정보 저장
        modalData: {}, // 모달에서 사용할 데이터 객체
        showModal : false,
      };
    },
    computed: {
    // 실제 필드 정의에서 visibleFields에 따라 fields를 필터링하는 computed 속성
      filteredFields() {
        return this.fields.filter(field => this.visibleFields.includes(field.key));
      },
    },
    methods: {
      fetchIndvInfoList() {
        console.log("hi");
        console.log("comp_id: " + this.$route.params.comp_id);
        // const comp_id = this.$route.params.comp_id; // 경로 매개변수 사용
        http.get("/get-candidate",{ params: { comp_id: this.$route.params.comp_id} })
          .then(response => {
            this.indvInfoList = response.data;
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
      },
      handleRowClick(item) {
      // 클릭된 행에 대한 처리 추가
        this.modalData = {
          c: item.c,
          e: item.e,
          f: item.f,
          g: item.g,
          h: item.h,
          i: item.i,
          score: (parseFloat(item.score)).toFixed(2) + "%"
          // 나머지 필드 추가
        };
        console.log("Row clicked g:", item.g);
        this.clickedRow = item;
        console.log("Clicked Row Data:", this.clickedRow);
        this.showModal = true; // 모달을 표시
        console.log("isOpen?: " + this.showModal);
      },
      handleModalClose() {
        // 모달이 닫힐 때 clickedRow 초기화
        this.clickedRow = null;
        this.showModal = false; // 모달을 닫음
        console.log("isClose?: " + this.showModal);
        this.modalData = {};
      },
    },
    mounted() {
      this.fetchIndvInfoList();
    },
  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .hello{
    padding: 30px
  }
  </style>