<template>
  <div id="home">
    <br>
    <div class="center-breadcrumb">
      <el-breadcrumb separator="|">
        <el-breadcrumb-item :to="{ path: '/login' }" class="enlarge-font">退出</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: '/python'}" class="enlarge-font">python</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: '/java'}" class="enlarge-font">java</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: '/google'}" class="enlarge-font">google search</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <br>
<!--主界面包含四个跳转链接-->

      <ai />
   </div> 
  </template>
  
  <script>
  import axios from 'axios';
  import ai from './ai.vue'
  export default {
    components:{
      ai,
    },
    data() {
      return {
        textarea: '',
        responseData: '',
      };
    },
    methods: {  
      async queryai() {
        const data = {
          texture: this.textarea,
        };
        try {
          const response = await axios.post('http://localhost:8088/queryai', data);
  const rawResponseData = response.data; // 原始的字符串数据
  
  // 将字符串中的 \n 替换为实际的换行符，并移除 ///
  const replacedResponseData = rawResponseData.replace(/\\n/g, '\n').replace(/\/{3}/g, '');
  
  this.responseData = replacedResponseData;
  console.log(this.responseData);
        } catch (error) {
          // 处理错误
        }
      },
    },
  };
  </script>
  <style>
    /* Center the el-breadcrumb component */
    .center-breadcrumb {
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center;
    }
  
    /* Enlarge the font size of el-breadcrumb-item */
    .enlarge-font {
      font-size: 25px; /* Adjust the font size as needed */
      background-color:rgb(226, 230, 139);
      
    }
  
    .response-box {
    border: 1px solid #ccc;
    padding: 10px;
    margin-top: 10px;
    white-space: pre-wrap; /* 保留显示文本中的换行符 */
  }
  </style>
  
  