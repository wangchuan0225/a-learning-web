<template>
<div class="ai">
<div>
    <el-input
  type="textarea"
  class="input"
  :rows="2"
  placeholder="请输入需要问询讯飞ai内容"
  v-model="textarea">
</el-input>
</div>
<div>
<el-button @click="queryai">query</el-button>
  </div>
  <div class="response-box">
      {{ responseData }}
    </div>
 </div> 
</template>

<script>
import axios from 'axios';

export default {
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
        const rawResponseData = response.data;
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
    border: 1px solid #000; /* 设置边框样式，可以根据需要进行调整 */
  padding: 10px; /* 设置内边距，以增加框内内容与边框的距离 */
  margin: 10px 0; /* 设置外边距，以控制框与周围元素之间的距离 */
}
.input{
  width: 50%;
  margin-left: auto;
  margin-right: auto;
  height: 80px;
}
</style>

