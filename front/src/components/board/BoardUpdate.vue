<template>
  <div class="container mt-3">
    <div class="row">
      <div class="col">
        <div class="alert alert-primary"><h3>글수정</h3></div>
      </div>
    </div>
    <board-input-item type="update" />
  </div>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardUpdate",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
