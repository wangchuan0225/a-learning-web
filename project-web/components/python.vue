<template>
    <div>
    <div>
        <el-table :data="tableData" stripe class="table" :header-cell-style="{fontSize:'20px',color:'#e3dad5'}">
    <el-table-column prop="title" label="标题" class="element"></el-table-column>
    <el-table-column label="链接" class="element">
      <template slot-scope="scope">
        <a :href="scope.row.link" target="_blank">{{ scope.row.link }}</a>
      </template>
    </el-table-column>
  </el-table>
    </div>
    <div>
        <el-button @click="search">search</el-button> <el-button @click="updata">updata</el-button>
    </div>
    
    <br>
    <ai />
    </div>
  </template>
  
  <script>
  import ai from './ai.vue'
  import axios from 'axios';
    export default {
        components:{
            ai,
        },
      data() {
        return {
          tableData: []
        }
      },
      methods:{
            async search(){
                try {
                    // 发送POST请求到后端的特定端点
                const response = await axios.post('http://localhost:8088/learning/python');
                this.tableData=response.data
                    // 根据需要处理响应
                console.log('来自后端的响应:', response.data);
                    // 在发送到后端后重置 sentence1 和 sentence2（可选）
                } catch (error) {
                    console.error('发送数据到后端时出错:', error);
                    this.tableData = null;
                    // 在控制台输出错误信息
                    console.log('发送数据到后端时出错:', error);
                    // 使用弹窗输出错误信息
                    }
            },
            async updata(){
                    const response= await axios.post('http://localhost:8088/update/python')
            }
      }
    }
  </script>
<style>
.table{
  width:100%;
  height: 350px;
 
};
.element{
  width: 300;
  text-align: center;
  
}
</style>

