<template>
<div>
  <br>
  <div>
  <el-input v-model="inputaccount" placeholder="请输入账号" class="custom-input"></el-input>
  </div>
  <div>
    <br>
    <el-input placeholder="请输入密码" v-model="inputpassword" show-password class="custom-input"></el-input>
    </div>
    <div>
      <br>
    <el-button @click="login">登录</el-button>
   <!--登录界面输入账号密码后按登录按钮调用login函数-->
    </div>
     <br>
</div> 
</template>
  <script>
  import axios from 'axios';
    export default {
      data() {
        return {
          inputpassword:'',
          inputaccount:'',
        };
      },
      methods: {
        async login(){
          const data = {
        account: this.inputaccount,
        password: this.inputpassword
        };
      // 接受输入端输入的账号密码
      // 发送POST请求
        try {
          const response = await axios.post('http://localhost:8088/login', data);
          // 处理成功的响应

          if (response.data === 2) {
            // 当返回值为2时说明为本人登录，跳转到home路径
            this.$router.push({ path:'/home'  })
          } else {
          // 处理其他情况
          console.log(response.data)
          alert("不是内部人员操作")
          }
        } 
          catch (error) {
        // 处理错误
        console.error('登录失败:', error);
          }
        },


        
      }
    };
  </script>
<style>
.custom-input {
  width: 300px; /* 设置输入框的宽度 */
  margin: 0 auto; /* 使输入框水平居中 */
}
</style>

