function formatCurrency(amount) {
  return new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(amount);
}
function formatDateTime(rawDate) {
  const date = new Date(rawDate);

  return `${date.getDate()} tháng ${
    date.getMonth() + 1
  }, ${date.getFullYear()}, ${date.getHours()}:${String(
    date.getMinutes()
  ).padStart(2, "0")}:${String(date.getSeconds()).padStart(2, "0")}`;
}
