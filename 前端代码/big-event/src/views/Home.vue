<template>

  <el-dialog v-model="dialogVisible" title="编辑简介" width="500">
    <el-space direction="vertical">
      <el-input v-model="textarea" style="width: 465px" :rows="10" type="textarea" />
      <!-- <el-button type="success" style="width: 465px" @click="setIntroduction">提交</el-button> -->
      <div>
        <button class="btn" style="width: 465px" @click="setIntroduction"><i class="animation"></i>提交<i
            class="animation"></i>
        </button>
      </div>
    </el-space>
  </el-dialog>


  <el-card style="max-width: 100% ; max-height: 100%">
    <template #header>
      <div class="card-header">
        <!-- <el-button type="primary" round @click="dialogVisible = true"
          v-if="userInfo.info.role === 'admin'">编辑</el-button> -->
        <button class="comic-button" @click="dialogVisible = true" v-if="userInfo.info.role === 'admin'">编辑</button>
      </div>
    </template>
    <br>
    <br>
    <!-- <el-input v-model="Introduction" style="width: 100% " disabled :rows="10" type="textarea" placeholder="Please input" /> -->
    <!-- <input v-model="Introduction" type="textarea" disabled placeholder="Write here..." class="input" style="width: 97%"
      :rows="10"/> -->
    <div class="brutalist-container">
      <textarea rows="10" v-model="introduction.introduction" disabled style="width: 1060px" placeholder="TYPE HERE"
        class="brutalist-input smooth-type"  />
      <label class="brutalist-label">系统简介</label>
    </div>
    <template #footer>


    </template>

  </el-card>
</template>

<script setup>
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import userInfoStore from '@/stores/userInfo.js'
import useIntroductionStore from '@/stores/introduction.js'
const userInfo = userInfoStore();
const introduction = useIntroductionStore();
const dialogVisible = ref(false)
const textarea = ref('')
const setIntroduction = () => {
  introduction.setIntroduction(textarea.value)
  dialogVisible.value = false;
  textarea.value = ''
}
</script>

<style>
.comic-button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  color: #fff;
  background-color: #ff5252;
  border: 2px solid #000;
  border-radius: 10px;
  box-shadow: 5px 5px 0px #000;
  transition: all 0.3s ease;
  cursor: pointer;
}

.comic-button:hover {
  background-color: #fff;
  color: #ff5252;
  border: 2px solid #ff5252;
  box-shadow: 5px 5px 0px #ff5252;
}

.comic-button:active {
  background-color: #fcf414;
  box-shadow: none;
  transform: translateY(4px);
}

.input {
  border-radius: 10px;
  outline: 2px solid #FEBF00;
  border: 0;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  background-color: #e2e2e2;
  outline-offset: 3px;
  padding: 80px 1rem;
  transition: 0.25s;
}

.input:focus {
  outline-offset: 5px;
  background-color: #fff
}

.btn {
  outline: 0;
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  background: #40B3A2;
  min-width: 200px;
  border: 0;
  border-radius: 4px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, .1);
  box-sizing: border-box;
  padding: 16px 20px;
  color: #fff;
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 1.2px;
  text-transform: uppercase;
  overflow: hidden;
  cursor: pointer;
}

.btn:hover {
  opacity: .95;
}

.btn .animation {
  border-radius: 100%;
  animation: ripple 0.6s linear infinite;
}

@keyframes ripple {
  0% {
    box-shadow: 0 0 0 0 rgba(255, 255, 255, 0.1), 0 0 0 20px rgba(255, 255, 255, 0.1), 0 0 0 40px rgba(255, 255, 255, 0.1), 0 0 0 60px rgba(255, 255, 255, 0.1);
  }

  100% {
    box-shadow: 0 0 0 20px rgba(255, 255, 255, 0.1), 0 0 0 40px rgba(255, 255, 255, 0.1), 0 0 0 60px rgba(255, 255, 255, 0.1), 0 0 0 80px rgba(255, 255, 255, 0);
  }
}


.brutalist-container {
  position: relative;
  width: 250px;
  font-family: monospace;
}

.brutalist-input {
  width: 100%;
  padding: 15px;
  font-size: 18px;
  font-weight: bold;
  color: #000;
  background-color: #fff;
  border: 4px solid #000;
  position: relative;
  overflow: hidden;
  border-radius: 0;
  outline: none;
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
  box-shadow: 5px 5px 0 #000, 10px 10px 0 #4a90e2;
}

@keyframes glitch {
  0% {
    transform: translate(0);
  }

  20% {
    transform: translate(-2px, 2px);
  }

  40% {
    transform: translate(-2px, -2px);
  }

  60% {
    transform: translate(2px, 2px);
  }

  80% {
    transform: translate(2px, -2px);
  }

  100% {
    transform: translate(0);
  }
}

.brutalist-input:focus {
  animation: focus-pulse 4s cubic-bezier(0.25, 0.8, 0.25, 1) infinite,
    glitch 0.3s cubic-bezier(0.25, 0.8, 0.25, 1) infinite;
}

.brutalist-input:focus::after {
  content: "";
  position: absolute;
  top: -2px;
  left: -2px;
  right: -2px;
  bottom: -2px;
  background: white;
  z-index: -1;
}

.brutalist-input:focus::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: black;
  z-index: -2;
  clip-path: inset(0 100% 0 0);
  animation: glitch-slice 4s steps(2, end) infinite;
}

@keyframes glitch-slice {
  0% {
    clip-path: inset(0 100% 0 0);
  }

  10% {
    clip-path: inset(0 5% 0 0);
  }

  20% {
    clip-path: inset(0 80% 0 0);
  }

  30% {
    clip-path: inset(0 10% 0 0);
  }

  40% {
    clip-path: inset(0 50% 0 0);
  }

  50% {
    clip-path: inset(0 30% 0 0);
  }

  60% {
    clip-path: inset(0 70% 0 0);
  }

  70% {
    clip-path: inset(0 15% 0 0);
  }

  80% {
    clip-path: inset(0 90% 0 0);
  }

  90% {
    clip-path: inset(0 5% 0 0);
  }

  100% {
    clip-path: inset(0 100% 0 0);
  }
}

.brutalist-label {
  position: absolute;
  left: -3px;
  top: -35px;
  font-size: 14px;
  font-weight: bold;
  color: #fff;
  background-color: #000;
  padding: 5px 10px;
  transform: rotate(-1deg);
  z-index: 1;
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}

.brutalist-input:focus+.brutalist-label {
  transform: rotate(0deg) scale(1.05);
  background-color: #4a90e2;
}

.smooth-type {
  position: relative;
  overflow: hidden;
}

.smooth-type::before {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: linear-gradient(90deg, #fff 0%, rgba(255, 255, 255, 0) 100%);
  z-index: 1;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.smooth-type:focus::before {
  opacity: 1;
  animation: type-gradient 2s linear infinite;
}

@keyframes type-gradient {
  0% {
    background-position: 300px 0;
  }

  100% {
    background-position: 0 0;
  }
}

.brutalist-input::placeholder {
  color: #888;
  transition: color 0.3s ease;
}

.brutalist-input:focus::placeholder {
  color: transparent;
}

.brutalist-input:focus {
  animation: focus-pulse 4s cubic-bezier(0.25, 0.8, 0.25, 1) infinite;
}

@keyframes focus-pulse {

  0%,
  100% {
    border-color: #000;
  }

  50% {
    border-color: #4a90e2;
  }
}
</style>
