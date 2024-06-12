<template>
    <div class="box">
        <h2>{{ name }}</h2>
    </div>
</template>
<script>
    export default {
        name:'DayRoutine'
    }
</script>
<script setup>
    let name = 'DayRoutine'
</script>

<style>
    .box{
        background-color: skyblue;
        box-shadow: 0 0 10px;
        border-radius: 10px;
        padding: 20px;
    }
</style>