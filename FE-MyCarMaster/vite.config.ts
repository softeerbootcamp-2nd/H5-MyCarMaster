import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import path from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: [
      { find: "@", replacement: path.resolve(__dirname, "src") },
      { find: "@pages", replacement: path.resolve(__dirname, "src/pages") },
      {
        find: "@common",
        replacement: path.resolve(__dirname, "src/components/common"),
      },
      { find: "@utils", replacement: path.resolve(__dirname, "src/utils") },
      { find: "@assets", replacement: path.resolve(__dirname, "src/assets") },
      { find: "@styles", replacement: path.resolve(__dirname, "src/styles") },
      { find: "@hooks", replacement: path.resolve(__dirname, "src/hooks") },
      {
        find: "@contexts",
        replacement: path.resolve(__dirname, "src/contexts"),
      },
      {
        find: "@constants",
        replacement: path.resolve(__dirname, "src/constants"),
      },
      { find: "@types", replacement: path.resolve(__dirname, "src/types") },
      {
        find: "@layout",
        replacement: path.resolve(__dirname, "src/components"),
      },
    ],
  },
});
