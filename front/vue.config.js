module.exports = {  
    outputDir: "../src/main/resources/static",  
    indexPath: "../static/index.html",  
    devServer: {  
      // proxy: "http://165.132.221.100:8080" // 스프링 boot 내장 was 주소
      proxy: "http://localhost:8080"
    },  
    chainWebpack: config => {  
      const svgRule = config.module.rule("svg");    
      svgRule.uses.clear();    
      svgRule.use("vue-svg-loader").loader("vue-svg-loader");  
    }  
  };