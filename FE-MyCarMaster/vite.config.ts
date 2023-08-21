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

      // layout views
      {
        find: "@layout_home",
        replacement: path.resolve(__dirname, "src/components/Home"),
      },
      {
        find: "@layout_header",
        replacement: path.resolve(__dirname, "src/components/Header"),
      },
      {
        find: "@layout_footer",
        replacement: path.resolve(__dirname, "src/components/Footer"),
      },
      {
        find: "@layout_main",
        replacement: path.resolve(__dirname, "src/components/Main"),
      },

      // common components
      {
        find: "@common_button",
        replacement: path.resolve(__dirname, "src/components/common/Button"),
      },
      {
        find: "@common_category",
        replacement: path.resolve(
          __dirname,
          "src/components/common/CategoryList"
        ),
      },
      {
        find: "@common_modals",
        replacement: path.resolve(__dirname, "src/components/common/modals"),
      },
      {
        find: "@common_option_box",
        replacement: path.resolve(__dirname, "src/components/common/OptionBox"),
      },
    ],
  },
});
