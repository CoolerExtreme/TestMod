package cooler.testmod.client;
 
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
 
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
 
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.model.PositionTextureVertex;
 
import net.minecraftforge.client.model.obj.Vertex;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.techne.TechneModel;
 
import cpw.mods.fml.common.ObfuscationReflectionHelper;
 
public class ModelObj
{
    public static List<GroupObject> bakeModel(ModelRenderer model)
    {
        return bakeModel(model, 1);
    }
 
    public static List<GroupObject> bakeModel(ModelRenderer model, float scale)
    {
        return bakeModel(model, scale, new Matrix4f());
    }
 
    public static List<GroupObject> bakeModel(ModelRenderer model, float scale, Matrix4f matrix)
    {
        return bakeModel(model, scale, matrix, false);
    }
 
    /**
     * Convert ModelRenderer to a list of GroupObjects, for ease of use in ISBRH and other static contexts.
     * @param scale the scale factor, usually last argument to rendering methods
     * @param matrix initial transformation matrix (replaces calling glTranslate/glRotate/e.t.c. before rendering)
     * @param rotateYFirst true, of the order of rotations be like in ModelRenderer.renderWithRotation, false if like in ModelRenderer.render
     */
    @SuppressWarnings("unchecked")
    public static List<GroupObject> bakeModel(ModelRenderer model, float scale, Matrix4f matrix, boolean rotateYFirst)
    {
        Matrix4f m = new Matrix4f(matrix);
        m.translate(new Vector3f(
            model.offsetX + model.rotationPointX * scale,
            model.offsetY + model.rotationPointY * scale,
            model.offsetZ + model.rotationPointZ * scale
        ));
        if(!rotateYFirst) m.rotate(model.rotateAngleZ, new Vector3f(0, 0, 1));
        m.rotate(model.rotateAngleY, new Vector3f(0, 1, 0));
        m.rotate(model.rotateAngleX, new Vector3f(1, 0, 0));
        if(rotateYFirst) m.rotate(model.rotateAngleZ, new Vector3f(0, 0, 1));
 
        Vector4f vec = new Vector4f();
        List<GroupObject> res = new ArrayList<GroupObject>();
        for(ModelBox box : (List<ModelBox>)model.cubeList)
        {
            GroupObject obj = new GroupObject("", GL11.GL_QUADS);
            TexturedQuad[] quads = (TexturedQuad[])ObfuscationReflectionHelper.getPrivateValue(ModelBox.class, box, "quadList", "field_78254_i");
            for(int i = 0; i < quads.length; i++)
            {
                Face face = new Face();
                face.vertices = new Vertex[4];
                face.textureCoordinates = new TextureCoordinate[4];
                for(int j = 0; j < 4; j++)
                {
                    PositionTextureVertex pv = quads[i].vertexPositions[j];
                    
                    vec.x = (float)pv.vector3D.xCoord * scale;
                    vec.y = (float)pv.vector3D.yCoord * scale;
                    vec.z = (float)pv.vector3D.zCoord * scale;
                    vec.w = 1;
 
                    m.transform(m, vec, vec);
 
                    face.vertices[j] = new Vertex(
                        vec.x / vec.w,
                        vec.y / vec.w,
                        vec.z / vec.w
                    );
 
                    face.textureCoordinates[j] = new TextureCoordinate(
                        pv.texturePositionX,
                        pv.texturePositionY
                    );
                }
                obj.faces.add(face);
            }
            res.add(obj);
        }
        return res;
    }
 
    public static Map<String, GroupObject> bakeModel(TechneModel model)
    {
        return bakeModel(model, 1);
    }
 
    public static Map<String, GroupObject> bakeModel(TechneModel model, float scale)
    {
        return bakeModel(model, scale, new Matrix4f());
    }
 
    public static Map<String, GroupObject> bakeModel(TechneModel model, float scale, Matrix4f m)
    {
        return bakeModel(model, scale, m, false);
    }
 
    /**
     * Use this to convert TechneModel to it's static representation
     */
    public static Map<String, GroupObject> bakeModel(TechneModel model, float scale, Matrix4f m, boolean rotateYFirst)
    {
        // I hate java type noise
        Map<String, ModelRenderer> parts = (Map<String, ModelRenderer>)ObfuscationReflectionHelper.getPrivateValue(TechneModel.class, model, "parts");
        Map<String, GroupObject> res = new HashMap<String, GroupObject>();
        for(Map.Entry<String, ModelRenderer> e : parts.entrySet())
        {
            GroupObject obj = bakeModel(e.getValue(), scale, m, rotateYFirst).get(0);
            res.put(e.getKey(), obj);
        }
        return res;
    }
}