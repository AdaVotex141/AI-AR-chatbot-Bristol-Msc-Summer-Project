<template>
    <div class="dashboard-container" id="member-app">
    <div class="container">
      <div class="tableBar">
        <el-button
          type="primary"
          @click="addMemberHandle('add')"
        >
          + 添加员工
        </el-button>
      </div>
      <el-table
        :data="tableData"
        stripe
        class="tableBox"
      >
        <el-table-column
          prop="username"
          label="USERNAME"
        ></el-table-column>
        <el-table-column label="账号状态">
          <template slot-scope="scope">
            {{ String(scope.row.status) === '0' ? '已禁用' : '正常' }}
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="160"
          align="center"
        >
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              class="blueBug"
              @click="addMemberHandle(scope.row.id)"
              :class="{notAdmin:userInfoStore.user !== 'admin'}"
            >
              编辑
            </el-button>
            <el-button
              type="text"
              size="small"
              class="delBut non"
              @click="statusHandle(scope.row)"
              v-if="userInfoStore.user === 'admin'"
            >
              {{ scope.row.status == '1' ? '禁用' : '启用' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useMemberStore } from '@/stores/member';
import { useUserInfoStore } from '@/stores/userInfo';
import { ref } from 'vue';

const userInfoStore = useUserInfoStore()
const memberStore = useMemberStore()

let input = ref('')
let counts = ref(0)
let tableData = ref([])
let id = ref('')
let status = ref('')

async function init(){
    
}

</script>

<style scoped>
    
</style>