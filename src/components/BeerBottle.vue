<template>
  <div class="bottle-wrap">
    <div class="bottle" :style="{ '--accent': accent }">
      <div class="bottle-neck"></div>
      <div class="bottle-shoulder"></div>
      <div class="bottle-body">
        <div class="label">
          <div class="label-title">{{ title }}</div>
        </div>
        <div class="capsule"></div>
        <div class="reflection"></div>
        <div class="sparkles">
          <span v-for="n in 4" :key="n" class="sparkle"></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  accent: {
    type: String,
    default: 'linear-gradient(135deg, #8b1e3f, #3ea76a)'
  },
  title: {
    type: String,
    default: 'Altération'
  }
})
</script>

<style scoped>
.bottle-wrap {
  display: grid;
  place-items: center;
  padding: 6px;
}
.bottle {
  position: relative;
  width: 110px;
  height: 260px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.06), rgba(15, 10, 18, 0.92));
  border-radius: 28px 28px 18px 18px;
  border: 1px solid rgba(255, 255, 255, 0.1);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.35);
  overflow: hidden;
}
.bottle-neck {
  position: absolute;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 34px;
  height: 70px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.12), rgba(0, 0, 0, 0.46));
  border-radius: 12px 12px 10px 10px;
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.bottle-shoulder {
  position: absolute;
  top: 60px;
  left: 50%;
  transform: translateX(-50%);
  width: 64px;
  height: 32px;
  background: radial-gradient(circle at 50% 30%, rgba(255, 255, 255, 0.1), transparent 65%);
}
.bottle-body {
  position: absolute;
  inset: 72px 12px 12px 12px;
  border-radius: 18px 18px 14px 14px;
  background: linear-gradient(145deg, rgba(255, 255, 255, 0.04), rgba(255, 255, 255, 0.02)), var(--accent, linear-gradient(135deg, #8b1e3f, #3ea76a));
  box-shadow: inset 0 0 0 1px rgba(255, 255, 255, 0.06);
}
.capsule {
  position: absolute;
  top: -62px;
  left: 50%;
  transform: translateX(-50%);
  width: 42px;
  height: 30px;
  background: linear-gradient(135deg, rgba(255, 255, 255, 0.08), rgba(255, 255, 255, 0.14));
  border-radius: 12px 12px 8px 8px;
  box-shadow: 0 6px 14px rgba(0, 0, 0, 0.25);
  border: 1px solid rgba(255, 255, 255, 0.14);
}
.label {
  position: absolute;
  bottom: 18px;
  left: 50%;
  transform: translateX(-50%);
  width: 76%;
  padding: 9px;
  border-radius: 10px;
  background: rgba(15, 10, 18, 0.88);
  border: 1px solid rgba(255, 255, 255, 0.12);
  text-align: center;
  color: #f6ecf0;
  box-shadow: 0 8px 18px rgba(0, 0, 0, 0.24);
}
.label-title {
  margin: 0;
  font-size: 14px;
  font-weight: 700;
  line-height: 1.2;
}
.reflection {
  position: absolute;
  inset: 18px auto 42px 14px;
  width: 14px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.6), transparent 70%);
  opacity: 0.35;
  filter: blur(1px);
}
.sparkles {
  position: absolute;
  inset: 20px 12px 60px 12px;
  pointer-events: none;
}
.sparkle {
  position: absolute;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.5);
  filter: blur(0.6px);
}
.sparkle:nth-child(1) { top: 12%; left: 18%; width: 5px; height: 5px; }
.sparkle:nth-child(2) { top: 32%; left: 62%; width: 7px; height: 7px; }
.sparkle:nth-child(3) { top: 58%; left: 44%; width: 4px; height: 4px; }
.sparkle:nth-child(4) { top: 70%; left: 22%; width: 6px; height: 6px; }
@media (max-width: 960px) {
  .bottle { width: 92px; height: 220px; }
  .bottle-neck { height: 58px; }
  .bottle-body { inset: 62px 8px 12px 8px; }
  .label-title { font-size: 13px; }
}
</style>
