<template>
  <AsideItem v-if="!boardCreated">
    <template #heading>Tablero</template>
    <div class="text-center" v-if="!boardCreated">Ingresa tu nombre de jugador y crear un tablero para una partida.</div>
    <p class="card-text placeholder-glow" v-if="loading">
      <span class="placeholder col-8"></span>
    </p>
  </AsideItem>
  <AsideItem v-if="boardCreated">
    <template #heading>15 hoyos</template>
    <div class="d-flex justify-content-center" v-if="currentMovement">Moviendo de {{ currentMovement.originX }}, {{ currentMovement.originY }} a {{ currentMovement.destinationX }}, {{ currentMovement.destinationY }}</div>
    <div class="d-flex flex-column align-items-center mt-4" v-if="board">
      <div class="d-flex justify-content-center gap-2" v-for="(level, i) in board.levels" :key="i">
        <div class="d-flex align-items-center justify-content-center bg-primary text-white rounded m-1" style="width: 40px; height: 40px;" v-for="(peg, j) in level" :key="j">
          {{ peg ? 'O' : '' }}
        </div>
      </div>
    </div>
    <div class="justify-content-center mt-4" v-if="history">
      <p v-for="item in history" :key="item"> {{ item }}</p>
    </div>
    
    <div class="d-flex justify-content-center mt-4">
      <button type="button" class="btn btn-outline-light" v-if="boardCreated && !playing" @click="play">Jugar</button>
    </div>
    <p class="card-text placeholder-glow" v-if="loading">
      <span class="placeholder col-8"></span>
    </p>
  </AsideItem>
</template>
<script setup>
import { inject, ref, watch } from 'vue';
import AsideItem from './AsideItem.vue'
import { getBoardState, movePeg, getHistoryByPlayerId } from '../api/pegGameApi';
const loading = inject('loading');
const boardCreated = inject('boardCreated');
const playerId = inject('playerId');
const board = ref(null);
const playing = ref(false);
const history = ref([]);
const movements = [
    {
        "originX": 2,
        "originY": 2,
        "intermediateX": 1,
        "intermediateY": 1,
        "destinationX": 0,
        "destinationY": 0
    },
    {
        "originX": 2,
        "originY": 0,
        "intermediateX": 2,
        "intermediateY": 1,
        "destinationX": 2,
        "destinationY": 2
    }, {
        "originX": 0,
        "originY": 0,
        "intermediateX": 1,
        "intermediateY": 0,
        "destinationX": 2,
        "destinationY": 0
    }, {
        "originX": 4,
        "originY": 3,
        "intermediateX": 3,
        "intermediateY": 2,
        "destinationX": 2,
        "destinationY": 1
    }, {
        "originX": 4,
        "originY": 1,
        "intermediateX": 4,
        "intermediateY": 2,
        "destinationX": 4,
        "destinationY": 3
    }, {
        "originX": 2,
        "originY": 0,
        "intermediateX": 3,
        "intermediateY": 1,
        "destinationX": 4,
        "destinationY": 2
    }, {
        "originX": 4,
        "originY": 0,
        "intermediateX": 3,
        "intermediateY": 0,
        "destinationX": 2,
        "destinationY": 0
    }, {
        "originX": 4,
        "originY": 3,
        "intermediateX": 4,
        "intermediateY": 2,
        "destinationX": 4,
        "destinationY": 1
    }, {
        "originX": 3,
        "originY": 3,
        "intermediateX": 2,
        "intermediateY": 2,
        "destinationX": 1,
        "destinationY": 1
    }, {
        "originX": 1,
        "originY": 1,
        "intermediateX": 2,
        "intermediateY": 1,
        "destinationX": 3,
        "destinationY": 1
    }, {
        "originX": 2,
        "originY": 0,
        "intermediateX": 3,
        "intermediateY": 1,
        "destinationX": 4,
        "destinationY": 2
    },
    {
        "originX": 4,
        "originY": 1,
        "intermediateX": 4,
        "intermediateY": 2,
        "destinationX": 4,
        "destinationY": 3
    },
    {
        "originX": 4,
        "originY": 4,
        "intermediateX": 4,
        "intermediateY": 3,
        "destinationX": 4,
        "destinationY": 2
    }
]
const currentMovement = ref(null);

watch(boardCreated, async (newValue) => {
  if (newValue) {
    loading.value = true;
    board.value = await getBoardState(playerId.value);
    loading.value = false;
  }
}, { once: true });

const play = async () => {
  playing.value = true;
  currentMovement.value = movements[0];
  for (let i = 0; i < movements.length; i++) {
    await new Promise(resolve => setTimeout(resolve, 2000));
    board.value = await movePeg(playerId.value, movements[i]);
    if (movements[i+1]) {
      currentMovement.value = movements[i+1];
    }
  }
  history.value = await getHistoryByPlayerId(playerId.value);
}
</script>