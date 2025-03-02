<template>
  <header>
    <img alt="Vue logo" class="logo" src="./assets/logo.png" />
    <div class="wrapper">
      <Player/>
    </div>
  </header>

  <main>
    <TheBoard />
  </main>
</template>
<script setup>
import { provide, ref } from 'vue';
import Player from './components/Player.vue';
import TheBoard from './components/TheBoard.vue'
const loading = ref(false);
const boardCreated = ref(false);
const playerId = ref(null);
provide('loading', loading);
provide('boardCreated', boardCreated);
provide('playerId', playerId);
</script>
