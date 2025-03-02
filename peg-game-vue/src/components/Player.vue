<template>
    <div class="player">
        <h1 class="green">Usuario: {{ player }}</h1>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Nuevo usuario</span>
            <input type="text" class="form-control" placeholder="Nombre" aria-label="Jugador"
                aria-describedby="basic-addon1" v-model="player">
        </div>
        <button type="button" class="btn btn-outline-success" v-if="!boardCreated" v-on:click="createBoard">Crear tablero</button>
        <p class="text-danger" v-if="error">Ocurrio un error al interactual con el API: {{ error }}</p>
    </div>
</template>

<script setup>
import { ref, inject } from 'vue';
import { createMatch } from '../api/pegGameApi';
const player = ref('Player 1');
const boardCreated = inject('boardCreated');
const loading = inject('loading');
const playerId = inject('playerId');
const error = ref(null);

const createBoard = async () => {
    loading.value = true;
    try {
        const board = await createMatch(player.value);
        playerId.value = board.playerId;
        boardCreated.value = true;
    } catch (err) {
        error.value = err.stack;
        console.error(err);
    }
    loading.value = false;
}

</script>