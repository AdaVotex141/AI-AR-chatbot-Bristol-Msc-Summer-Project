<template>
    <div>
        <el-form-item :label="labelName" :prop="propName">
            <el-input v-model="form"></el-input>
        </el-form-item>
    </div>
</template>

<script lang='ts' setup>
import { toRefs, ref, computed } from 'vue';


const props = defineProps({
    labelName: String,
    propName: String,
    form: String
})

const {labelName, propName, form} = toRefs(props)


</script>


<style scoped>

</style>
