<template>
  <div class="home">
    <HelloWorld style="display: none;" msg="Welcome to Your Vue.js App" />
    <div class="body">
      asdf
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>

<style>
.body {
  margin: 0;
  /* 기본 마진 제거 */
  padding: 0;
  /* 기본 패딩 제거 */
  height: 100vh !important;
  /* 화면 높이의 100%로 설정 */
  overflow: hidden;
  /* 오버플로우 숨김 (필요한 경우) */
}
</style>
