﻿using System;
using UIKit;
using Foundation;
using System.Linq;
using System.Collections.Generic;

namespace Kaptal.iOS {
    public class Application {
        // This is the main entry point of the application.
        static void Main(string[] args) {
            // if you want to use a different Application Delegate class from "AppDelegate"
            // you can specify it here.
            UIApplication.Main(args, null, "AppDelegate");
        }
    }
}
