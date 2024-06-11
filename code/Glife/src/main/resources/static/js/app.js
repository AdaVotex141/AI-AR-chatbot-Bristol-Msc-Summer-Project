Vue.component('Chat', {
    props: ['username'],
    data() {
        return {
            messages: [],
            newMessage: ''
        };
    },
    methods: {
        sendMessage() {
            // 发送消息给后端，并将返回的回复添加到 messages 数组中
            axios.post('/api/chat', { message: this.newMessage })
                .then(response => {
                    this.messages.push({ text: this.newMessage, fromUser: true });
                    this.messages.push({ text: response.data.text, fromUser: false });
                    this.newMessage = ''; // 清空输入框
                })
                .catch(error => {
                    console.error('Error sending message:', error);
                });
        }
    },
    template: `
        <div class="chat-container">
            <div class="chat-messages">
                <div v-for="(message, index) in messages" :key="index" :class="{ 'from-user': message.fromUser, 'from-bot': !message.fromUser }">
                    {{ message.text }}
                </div>
            </div>
            <div class="chat-input">
                <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type your message...">
                <button @click="sendMessage">Send</button>
            </div>
        </div>
    `
});

new Vue({
    el: '#app',
    data() {
        return {
            username: '' // 将在登录成功时设置为用户的用户名
        };
    },
    methods: {
        logout() {
            // 登出逻辑
            axios.post('/api/logout')
                .then(response => {
                    window.location.href = 'index.html'; // 登出成功后跳转到登录页面
                })
                .catch(error => {
                    console.error('Logout failed:', error);
                    alert('Logout failed. Please try again.');
                });
        }
    },
    mounted() {
        // 获取用户信息
        axios.get('/api/user')
            .then(response => {
                this.username = response.data.username;
            })
            .catch(error => {
                console.error('Failed to fetch user data:', error);
            });
    }
});
