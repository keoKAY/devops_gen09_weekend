## This is just the demo project
```bash 
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p

// tailwind.config.js
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
```


### Uninstall the tailwinds 
```bash
npm uninstall tailwindcss postcss autoprefixer
rm tailwind.config.js postcss.config.js

```