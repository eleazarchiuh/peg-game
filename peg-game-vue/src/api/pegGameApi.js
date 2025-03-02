import apiClient from './pegGameClient';
export const createMatch = async (player) => {
    const response = await apiClient.post('/peg-game/match', { player: player });
    return response.data;
}

export const getBoardState = async (player) => {
    const response = await apiClient.get(`/peg-game/board/${player}` );
    return response.data;
}

export const movePeg = async (player, request) => {
    const response = await apiClient.post(`/peg-game/move/${player}`, request);
    return response.data;
}

export const getHistoryByPlayerId = async (player) => {
    const response = await apiClient.get(`/peg-game/board/history/${player}`);
    return response.data;
}