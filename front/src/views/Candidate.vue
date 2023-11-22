<template>
<div>
           <!-- 고객사 목록 table -->
  <div>
    <b-table striped hover :items="indvInfoList" :fields="filteredFields" @row-clicked="handleRowClick"></b-table>
  </div>
  <b-modal v-model="showModal" :title="'개인 정보'" @hide="handleModalClose">
      <li>
        <strong>[ 이름 ]</strong> 김병선 <br>
      </li>
      <li>
        <strong>[ 이메일 ]</strong> bkim8nfvhrcom <br>
      </li>
      <li>
        <strong>[ 전화번호 ]</strong> 61612467227 <br>
      </li>
      <li>
        <strong>[ 전문분야 ]</strong> 두산그룹 Digital Lean 체계 수립 및 변화관리 TMO 주관 Quantum Computing ESG Web 30 등 신기술 과제 기획 경험 그룹 관점의 AI Data 기반 Digital Transformation 과제 기획 경험 IT PMI 경험 및 그룹차원의 IT Merger Playbook 개발 경험 Digital Transformation 실행 경험 대규모 조직관리 리더쉽 60명  대기업 그룹의 IT 전략 수립 IT 예산 수립분석개선 IT 인프라 및 공통 업무서비스 전략과 도입 신기술 도입 경험 10년 국내 대기업 지주사 근무 경험 및 그룹 차원의 전략적 관점 보유 10년  대형 프로젝트 PM 경험 기획과 현장 경험 겸비 글로벌 마인드와 영어 구사력 자회사 반도체 공정 데이터 분석 과제 기회 YieldQualityCost 그룹 Digital Transformation DT 프레임워크 수립과 P I변화관리 AWS 기반 Enterprise Mass Migration 프로젝트 기획과 실행  그룹차원의 대형 프로젝트 PM IT 기획운영 조직의 변화관리DT 실행 Digital 직무와 FC 설계 변화관리  그룹 IT 전략 수립과 실행 데이터센터네트워크 통합 전략 수립과 실행 <br>
      </li>
      <li>
        <strong>[ 학력 ]</strong> University of Florida 미국 Computer Science and Engineering 석사숭실대학교 전자계산학과 학사 <br>
      </li>
      <li>
        <strong>[ 경력 ]</strong> 200711  현재14년 18개월 두산그룹 Head of Digitalization DT 총괄팀 에너빌리티테스나 담당 2202  현재200003  2007107년 8개월 에스넷시스템 마케팅팀 근무 팀장부장199507  2000024년 5개월 삼성SDS 정보통신본부 NW기술팀사업팀 근무 <br>
      </li>
      <li>
        <strong>[ 적합도 ]</strong> 33.32% <br>
      </li>
  </b-modal>
</div>    
  </template>
  
  <script>
  import http from "../http-common";
  import { BModal } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


  export default {
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
          { key: 'score', label: 'Score' },
          
        ],
        // 원하는 필드만 선택하여 표시
        visibleFields: ['c', 'e', 'f', 'score'],
        clickedRow: null, // 클릭한 행의 정보 저장
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
        http.get("/get-candidate")
          .then(response => {
            this.indvInfoList = response.data;
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
      },
      handleRowClick(item) {
      // 클릭된 행에 대한 처리 추가
        console.log("Row clicked:", item);
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