const userInfo = localStorage.getItem("info");
const avatarContainer = document.getElementById("avatar-container");

if (userInfo) {
  const parsedInfo = JSON.parse(userInfo);
  document.getElementById("nav-register").style.display = "none";
  document.getElementById("nav-login").style.display = "none";
  avatarContainer.innerHTML = `<img src="${parsedInfo.image}" alt="Ảnh cá nhân" class="w-full h-full object-cover" />`;
} else {
  document.getElementById("nav-register").style.display = "block";
  document.getElementById("nav-login").style.display = "block";
  document.getElementById("avatar-container").style.display = "none";
}
