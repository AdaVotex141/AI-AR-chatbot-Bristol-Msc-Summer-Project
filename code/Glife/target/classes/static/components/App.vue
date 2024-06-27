<template>
    <div id="app">
        <header>
            <h1>Chatbot App</h1>
        </header>
        <main>
            <Chat />
        </main>
    </div>
</template>

<script>
    import Chat from './components/Chat.vue';

    export default {
        name: 'App',
        components: {
            Chat
        }
    };
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    header {
        background-color: #007bff;
        padding: 10px 0;
    }

    header h1 {
        color: white;
    }
</style>
