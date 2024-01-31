<template>
    <div>
        <div>
            <el-input v-model="input" placeholder="请输入内容" class="input"></el-input>
            <el-button icon="el-icon-search" circle @click="search"></el-button>
        </div>
        <div>
            <el-table
            :data="tableData"
            height="250"
            border
            class="table"
            :header-cell-style="{fontSize:'20px',color:'#e3dad5'}"
            
            >
            <el-table-column
            prop="title"
            label="title"
            width="580"
            class="element"
            >
            </el-table-column>
            <el-table-column
            label="link"
            width="700"
            class="element">
            <template slot-scope="scope">
        <a :href="scope.row.link" target="_blank">{{ scope.row.link }}</a>
      </template>
            </el-table-column>
        </el-table>
        </div>
        <div>
          <br>
        <ai></ai>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import ai from '../components/ai.vue'
export default {
  components:{
      ai,
  },
  data() {
    return {
      input: '',
      tableData:[],
    }
  },
  methods:{
    async search(){
        try{
            const response=await axios.post('http://localhost:8088/google',{texture:this.input});
            this.tableData=response.data;
        }catch(error){

        }
    }
  }
}

</script>
<style>
.table{
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  background-color: antiquewhite;
  fit:true;
}
.input{
  width: 80%;
  margin-left: 0%;
}
</style>