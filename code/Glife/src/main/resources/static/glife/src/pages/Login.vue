<template>
    <div style="margin: 20px" />
    <el-form
      label-position="top"
      label-width="auto"
      :model="formLabelAlign"
      style="max-width: 600px"
    >
      <el-form-item label="Username">
        <el-input v-model="formLabelAlign.name" />
      </el-form-item>
      <el-form-item label="Password">
        <el-input v-model="formLabelAlign.region" />
      </el-form-item>
      <el-form-item label="Activity form">
        <el-input v-model="formLabelAlign.type" />
      </el-form-item>
    </el-form>
  </template>
  
  <script lang="ts" setup>
  import { reactive, ref } from 'vue'
  
  const formLabelAlign = reactive({
    name: '',
    region: '',
    type: '',
  })
  </script>
  