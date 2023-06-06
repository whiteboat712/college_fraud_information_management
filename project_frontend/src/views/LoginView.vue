<script setup>


</script>

<template>
  <div style="width: 100vw; height: 100vh; overflow: hidden; display: flex">
    <div style="flex: 1">
      <el-image
          style="width: 100%; height: 100%"
          fit="cover"
          src="https://images6.alphacoders.com/131/1314602.png"/>
    </div>
    <div style="width: 400px; background-color: lightblue">
      <router-view/>
    </div>
  </div>
</template>

<style scoped>

</style>